package com.demo.java7;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProcessingJSONResponse {

    public static void main(String[] args) {

        BufferedReader reader;
        String text;
        StringBuffer content = new StringBuffer();

        try {

            URL url = new URL("https://reqres.in/api/users?delay=5");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //conn.setRequestMethod("HEAD");
            conn.setRequestMethod("GET");

            //Server connection handshake timeout
            conn.setConnectTimeout(10000); //in ms ~10sec
            //Read Timeout
            conn.setReadTimeout(2000); // in ms ~2sec

            int statusCode = conn.getResponseCode();

            System.out.println("The response code is: " + statusCode);

            System.out.println("The Response Headers are: " + conn.getHeaderFields().toString());
            System.out.println("Content Length: " + conn.getHeaderField("Content-Length"));
            System.out.println("Content Type: " + conn.getHeaderField("Content-Type"));

            long responseSize = conn.getContentLengthLong();

            //if(!conn.getRequestMethod().equalsIgnoreCase("GET") && responseSize>0) {
            if(responseSize>0) {

                if (statusCode >= 200 && statusCode <= 299) {

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while ((text = reader.readLine()) != null) {
                        content.append(text);
                    }

                    reader.close();

                    String responseText = content.toString();

                    if (conn.getHeaderField("Content-Type").contains("json")) {
                        JSONObject jsonObject = new JSONObject(responseText);
                        System.out.println("JSON: \n" + jsonObject.toString(4));
                    } else {
                        System.out.println(responseText);
                    }

                } else {
                    System.out.println("The request is failed: " + conn.getResponseMessage());
                }

            }

            conn.disconnect();

        } catch (MalformedURLException malURL) {
            malURL.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
