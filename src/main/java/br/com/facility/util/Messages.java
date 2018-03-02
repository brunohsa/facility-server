package br.com.facility.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class Messages {

    private static final String PROPERTY_NAME = "messages.properties";

    public static String getMessage(String propertyName) {

        InputStream input = null;
        InputStreamReader reader = null;
        try {
            input = ClassLoader.getSystemResourceAsStream(PROPERTY_NAME);
            reader = new InputStreamReader(input, Charset.defaultCharset());

            Properties prop = new Properties();
            prop.load(reader);
            return prop.getProperty(propertyName);
        } catch (IOException ex) {
            //TODO REMOVER
            throw new RuntimeException();
        } finally {
            closeInputStream(input);
            closeInputStream(reader);
        }
    }

    private static void closeInputStream(Closeable closeable) {
        if(closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                //TODO REMOVER
                throw new RuntimeException();
            }
        }
    }
}
