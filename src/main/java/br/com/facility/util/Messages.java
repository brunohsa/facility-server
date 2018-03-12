package br.com.facility.util;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class Messages {

    private static final String PROPERTY_NAME = "messages.properties";

    private Messages() {
    }

    public static String getMessage(String propertyName) {
        if (StringUtils.isEmpty(propertyName)) {
            return "!" + propertyName;
        }
        try (InputStream input = ClassLoader.getSystemResourceAsStream(PROPERTY_NAME);
             InputStreamReader reader = new InputStreamReader(input, Charset.defaultCharset())) {

            Properties prop = new Properties();
            prop.load(reader);
            return prop.getProperty(propertyName);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
