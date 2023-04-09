package com.demo.java11;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SendMultipleAsyncRequests {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> urlList = new ArrayList<>();
        urlList.add("https://reqres.in/api/users/5");
        urlList.add("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        urlList.add("https://swapi.dev/api/planets/3/");

        HttpClient client = HttpClient.newHttpClient();

        Map<String, CompletableFuture<HttpResponse<String>>> responses = new HashMap<>();

        for (String url: urlList) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            CompletableFuture<HttpResponse<String>> future = null;
            future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            responses.put(url, future);
        }

        System.out.println("Requests Sent out. Ready do some other work now...");
        Thread.sleep(5000);
        System.out.println("Ready to process responses...");

        for (Map.Entry<String, CompletableFuture<HttpResponse<String>>> entry: responses.entrySet()) {

            String resp = entry.getValue().thenApply(HttpResponse::body).get();

            System.out.println("#URI of the response: " + entry.getKey());
            System.out.println("The response body is: \n");

            JSONObject jsonObject = new JSONObject(resp);
            System.out.println(jsonObject.toString(4));
        }

    }
}
