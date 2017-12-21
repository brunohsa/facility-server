package br.com.facility.util;

import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Messages {

    private static final String BUNDLE_MESSAGE = "messages";
    private static ResourceBundle bundle;

    public static String getMessage(String key) {
        if (Objects.isNull(key) || key.isEmpty()) {
            return "";
        }
        try {
            return getBundle().getString(key);
        } catch (MissingResourceException e) {
            return "!".concat(key);
        }
    }

    private static ResourceBundle getBundle() {
        if (Objects.isNull(bundle)) {
            bundle = ResourceBundle.getBundle(BUNDLE_MESSAGE, new MessagesUTF8Control());
        }
        return bundle;
    }
}
