package com.arudyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class UrlProcessor {

    public static String getPageAsString(String stringUrl) throws IOException {
        if (isHttp(stringUrl)) {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome/41.0.2228.0");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return read(connection);
            } else {
                throw new RuntimeException("URL is unreachable");
            }
        } else {
            throw new RuntimeException("Program accepts requests only http protocol");
        }
    }

    private static String read(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            result.append(nextLine);
        }
        reader.close();
        return result.toString();
    }

    private static boolean isHttp(String stringUrl) {
        if (stringUrl.startsWith("http://")) {
            return  true;
        } else {
            return false;
        }
    }
}