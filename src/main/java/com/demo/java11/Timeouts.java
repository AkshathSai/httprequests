package com.demo.java11;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Timeouts {

    public static void main(String[] args) {

        try {

            String uri = "https://reqres.in/api/users?delay=10";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET() //explicitly making a GET request but by default HttpRequest object is GET
                    //.timeout(Duration.ofSeconds(5)) // 5 seconds
                    .timeout(Duration.ofMillis(15000)) // ~ 15 sec in milli-sec
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("status code: " + response.statusCode());

            JSONObject jsonObject = new JSONObject(response.body());
            System.out.println(jsonObject.toString(4));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
