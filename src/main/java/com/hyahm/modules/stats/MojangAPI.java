package com.hyahm.modules.stats;

import java.net.HttpURLConnection;
import java.net.URL;

public class MojangAPI {
    public static boolean isPlayerReal(String s) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            } else if (connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
                return false;
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong!");
        }
    }
}
