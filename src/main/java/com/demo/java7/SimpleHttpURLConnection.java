package com.demo.java7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class SimpleHttpURLConnection
{
    public static void main( String[] args )
    {

        BufferedReader reader;
        String text;
        StringBuffer content = new StringBuffer();

        try {

            URL url = new URL("https://en.wikipedia.org/wiki/Windows_7");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            System.out.println("The status code is: " + statusCode);
            System.out.println("The Request Headers: " + conn.getHeaderFields());
            System.out.println("Content Length is: " + conn.getHeaderField("Content-Length"));
            System.out.println("Content Type is: " + conn.getHeaderField("Content-Type"));

            long responseSize = conn.getContentLengthLong();

            if(!conn.getRequestMethod().equalsIgnoreCase("GET") && responseSize>0) {

                System.out.println("The response body is: \n");

                if (statusCode >= 200 && statusCode <= 299) {

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while ((text = reader.readLine()) != null) {
                        content.append(text);
                    }

                    reader.close();
                    System.out.println(content.toString());
                } else {
                    System.out.println("The request failed: " + conn.getResponseMessage());
                }

            }

            conn.disconnect();

        } catch (MalformedURLException malurl) {
            malurl.printStackTrace();
        } catch (IOException iox) {
            iox.printStackTrace();
        }


    }
}
