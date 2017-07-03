package com.novaposhta.bot;

import com.novaposhta.bot.exception.BotException;
import com.novaposhta.bot.model.Response;
import com.novaposhta.bot.util.HttpUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.bots.commands.CommandRegistry;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainListenerBot extends TelegramLongPollingBot {

    private static Logger log = Logger.getLogger(MainListenerBot.class.getName());

    private static Properties me;

    public final static String API_URL = "https://api.novaposhta.ua/v2.0/json/";

    static {
        try {
            me = new Properties();
            me.load(new InputStreamReader(MainListenerBot.class.getResourceAsStream("/me.properties"), "UTF-8"));
        } catch (IOException e) {
            log.log(Level.WARNING, "Could not initialize properties: ", e);
        }
    }

    //TODO update the bot name
    public MainListenerBot() {
        CommandRegistry commandRegistry = new CommandRegistry(true, "novapochtabot");
        commandRegistry.registerAll(
                createStartCommand());
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        try {
            Long documentId = getDocumentId(update.getMessage().getText());
            Response response = HttpUtils.doHttpPost(API_URL, documentId, me);
            validateResponse(response);
            sendMessage(chatId.toString(), response.toString(), "sending message to the user.");
        } catch (BotException be) {
            log.log(Level.INFO, "Sending message to user:", be);
            sendMessage(chatId.toString(), be.getMessage(), "sending validation error to the user.");
        }
    }

    protected void validateResponse(Response response) {
        if (!response.isSuccess()) {
            throw new BotException(me.getProperty("message.box.not.registered"));
        }
    }

    private BotCommand createStartCommand() {
        return new BotCommand("start", "description") {
            @Override
            public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
                sendMessage(chat.getId().toString(), me.getProperty("message.welcome"),
                        "processing start command.");
            }
        };
    }

    protected Long getDocumentId(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException nfe) {
            throw new BotException(me.getProperty("message.document.incorrect"));
        }
    }

    protected boolean sendMessage(String chatId,
                                String text, String logMessage) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(text);
            sendMessage.setReplyMarkup(null); // TODO add if we need to make bot interactive
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            log.log(Level.WARNING, "Issue during " + logMessage, e);
            return false;
        }
        return true;
    }

    //TODO update the bot name
    @Override
    public String getBotUsername() {
        return "novapochtabot";
    }

    //TODO update the bot token
    @Override
    public String getBotToken() {
        return "123";
    }

}
