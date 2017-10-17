package br.com.facility.util;

import java.util.Objects;
import java.util.ResourceBundle;

public class Messages {

    private static final String BUNDLE_MESSAGE = "messages";
    private static ResourceBundle bundle;

    public static String getMessage(String key) {
        return getBundle().getString(key);
    }

    private static ResourceBundle getBundle() {
        if (Objects.isNull(bundle)) {
            bundle = ResourceBundle.getBundle(BUNDLE_MESSAGE, new MessagesUTF8Control());
        }
        return bundle;
    }
}
