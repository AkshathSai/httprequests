package com.demo.java11;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncResponseOnFile {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String url = "https://reqres.in/api/users?delay=10";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        System.out.println("Creating Future. Time: " + new Date());

        CompletableFuture<HttpResponse<Path>> future = null;
        future = client.sendAsync(
                request,
                HttpResponse.BodyHandlers.ofFile(Paths.get("response_async.txt")));

        System.out.println("Future created. Time: " + new Date());

        System.out.println("Sleeping for a few seconds...");
        Thread.sleep(8000);
        System.out.println("Waking up... Let's see where the response is!");

        Path resp = future.thenApply(HttpResponse::body).get();
        System.out.println("Parsing the response... Time: " + new Date());
        JSONObject jsonObject = new JSONObject(resp);
        System.out.println("JSON: " + jsonObject.toString(4));

    }
}
