package service;

import java.util.ResourceBundle;

/**
 * Implements reading data from user properties bundle
 */
public class UserPropertiesReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    /**
     * Gets value by the key from bundle
     *
     * @param key The key is a value by which the pair is searched in bundle
     * @return String value
     */
    public static String getUserData(String key) {
        return resourceBundle.getString(key);
    }
}
