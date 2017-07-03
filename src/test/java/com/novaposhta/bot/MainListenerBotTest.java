package com.novaposhta.bot;

import com.novaposhta.bot.exception.BotException;
import com.novaposhta.bot.model.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainListenerBotTest {

    MainListenerBot testObject = new MainListenerBot();

    @Test(expected = BotException.class)
    public void testGetDocumentIdNull() throws Exception {
        testObject.getDocumentId(null);
    }

    @Test(expected = BotException.class)
    public void testGetDocumentIdAlphabetic() throws Exception {
        testObject.getDocumentId("hello");
    }

    @Test
    public void testGetDocumentIdSuccess() throws Exception {
        Long response = testObject.getDocumentId("23489756283745");
        assertNotNull(response);
    }

    @Test
    public void testValidateResponseTrue() throws Exception {
        Response response = new Response();
        response.setSuccess(true);
        testObject.validateResponse(response);
    }

    @Test(expected = BotException.class)
    public void testValidateResponseFalse() throws Exception {
        Response response = new Response();
        response.setSuccess(false);
        testObject.validateResponse(response);
    }

    @Test
    public void testSendMessageNull() throws Exception {
        assertFalse(testObject.sendMessage(null, "text", "log"));
    }

    @Test
    public void testSendMessageEmptyMessage() throws Exception {
        assertFalse(testObject.sendMessage("12871235476354", "", "log"));
    }

    @Test
    public void testSendMessageSuccess() throws Exception {
        assertFalse(testObject.sendMessage("123", "123", "log"));
    }
}