package com.demo.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SavingResponseToFile {

    public static void main(String[] args) {

        try {

            String uri = "https://reqres.in/api/users";

            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .build();

            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("response.txt")));

            System.out.println("status code: " + response.statusCode());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
