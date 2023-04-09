package com.demo.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AllowRedirects {

    public static void main(String[] args) throws IOException, InterruptedException {

        String uri = "https://wikipedia.com";

        HttpClient client = HttpClient.newBuilder()
                //.followRedirects(HttpClient.Redirect.ALWAYS)
                .followRedirects(HttpClient.Redirect.NORMAL) //Always redirect, except from HTTPS URLs to HTTP URLs
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Status code - 301 will be printed confirming URL redirect
        System.out.println("Final Response Status Code: " + response.statusCode());
        System.out.println("Final Response URI: " + response.uri());
        System.out.println("Final Response Body: \n" + response.body());

        HttpResponse<String> previousResponse = response.previousResponse().get();
        System.out.println("Previous Response Status code: " + previousResponse.statusCode());
        System.out.println("Previous Response URI: \n" + previousResponse.uri());
        System.out.println("Previous Response Body: \n" + previousResponse.body());
    }
}
