package com.novaposhta.bot.util;

import com.novaposhta.bot.exception.BotException;
import org.junit.Test;

import java.util.Properties;

public class HttpUtilsTest {

    @Test(expected = BotException.class)
    public void testDoHttpPostFailed() throws Exception {
        HttpUtils.doHttpPost("http://123sjdnv234234442jksnsnc.com",
                123L, new Properties());
    }
}