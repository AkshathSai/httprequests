package com.demo.java7;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DeleteRequest {

    public static void main(String[] args) {

        String text;

        try {

            URL url = new URL("https://reqres.in/api/users/212");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");

            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Response Message: " + connection.getResponseMessage());

            long responseSize = connection.getContentLengthLong();
            System.out.println("Response size: " + responseSize);

            if (responseSize > 0) {

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {

                    StringBuilder responseText = new StringBuilder();

                    while ((text = reader.readLine()) != null) {
                        responseText.append(text.trim());
                    }

                    if (connection.getHeaderField("Content-type").contains("json")) {
                        JSONObject jsonObject = new JSONObject(responseText);
                        System.out.println("JSON:\n" + jsonObject.toString(4));
                    } else {
                        System.out.println(responseText.toString());
                    }

                }

            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
