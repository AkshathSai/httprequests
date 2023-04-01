package com.demo.java7;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendingPOSTRequest {

    public static void main(String[] args) {

        String text;

        try {

            URL url = new URL("https://reqres.in/api/users");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            String postData = "{'email': 'software@mafia.com', " +
                    "'firstName': 'Mafia', 'lastName': 'Kid'}";

            try(OutputStream outputStream = connection.getOutputStream()) {

                byte[] postBytes = postData.getBytes("utf-8");
                outputStream.write(postBytes, 0, postBytes.length);
            }

            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Response Message: " + connection.getResponseMessage());

            try(BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {

                StringBuilder responseText = new StringBuilder();

                while ((text = reader.readLine()) !=null) {
                    responseText.append(text.trim());
                }

                if (connection.getHeaderField("Content-type").contains("json")) {
                    JSONObject jsonObject = new JSONObject(responseText.toString());
                    System.out.println("JSON: \n" + jsonObject.toString(4));
                } else {
                    System.out.println(responseText.toString());
                }

            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
