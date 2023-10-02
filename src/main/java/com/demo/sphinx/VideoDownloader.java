package com.demo.sphinx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class VideoDownloader {

    public static void main(String[] args) {
        String webpageUrl = "https://www.youtube.com/watch?v=aJOTlE1K90k";
        String savePath = "video.mp4";

        try {
            // Connect to the webpage and parse its content
            Document doc = Jsoup.connect(webpageUrl).get();

            // Find the video element or specific HTML tags containing the video URL
            Elements videoElements = doc.select("video-stream");

            if (!videoElements.isEmpty()) {
                Element videoElement = videoElements.first();
                String videoUrl = videoElement.attr("src");

                // Download the video
                downloadVideo(videoUrl, savePath);
                System.out.println("Video downloaded successfully.");
            } else {
                System.out.println("No video element found on the webpage.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadVideo(String videoUrl, String savePath) throws IOException {
        URL url = new URL(videoUrl);
        URLConnection connection = url.openConnection();

        // Adjust connection properties if necessary
        connection.setConnectTimeout(5000); // 5 seconds
        connection.setReadTimeout(5000); // 5 seconds

        // Open input stream to read video data
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        // Open output stream to save video data
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);

        // Buffer for reading/writing data
        byte[] buffer = new byte[1024];
        int bytesRead;

        // Read data from input stream and write to output stream
        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        // Close streams
        fileOutputStream.close();
        bufferedInputStream.close();
    }
}
