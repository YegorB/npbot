package com.novaposhta.bot.util;


import com.google.gson.Gson;
import com.novaposhta.bot.exception.BotException;
import com.novaposhta.bot.model.Document;
import com.novaposhta.bot.model.Request;
import com.novaposhta.bot.model.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

public class HttpUtils {
    
    public static Response doHttpPost(String host, Long documentId, Properties me) {
        try {
            Request request = new Request();
            Document document = new Document();
            document.setDocumentNumber(documentId.toString());
            request.getMethodProperties().setDocuments(Arrays.asList(document));
            String requestBody = new Gson().toJson(request);

            URL url = new URL(host);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length",
                    Integer.toString(requestBody.getBytes().length));

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(requestBody);
            wr.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return new Gson().fromJson(response.toString(), Response.class);
        } catch (IOException ioe) {
            throw new BotException(me.getProperty("error.service.unavailable"));
        }
    }

}
