package service;

import java.util.ResourceBundle;

public class SearchRequestReader {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getSearchData(String key) {
        return resourceBundle.getString(key);
    }
}