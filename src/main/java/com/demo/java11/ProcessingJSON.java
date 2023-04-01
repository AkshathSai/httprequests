package com.demo.java11;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProcessingJSON {

    public static void main(String[] args) {

        try {

            URI uri = new URI("https://reqres.in/api/users");

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("status code: " + response.statusCode());

            JSONObject jsonObject = new JSONObject(response.body());
            System.out.println("Body: ");
            System.out.println("JSON:\n" + jsonObject.toString(4));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
