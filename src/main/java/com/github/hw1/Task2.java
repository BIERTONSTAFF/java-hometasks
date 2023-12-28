package com.github.hw1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Task2 {
    private static final HttpClient client = HttpClient.newHttpClient();

    private static int ping(URI uri) {
        HttpRequest req = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        int status = 0;

        try {
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            status = resp.statusCode();
        } catch (Exception ignored) {
            System.err.println("Unable to send request");
            System.exit(1);
        }

        return status;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: target INTERVAL URI");
            return;
        }

        Timer timer = new Timer();
        AtomicInteger attempt = new AtomicInteger(0);

        try {
            long interval = Long.parseLong(args[0]);
            AtomicReference<URI> uri = new AtomicReference<>();

            uri.set(URI.create(args[1]));
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!uri.get().isAbsolute()) {
                        System.err.println("Unable to parse URI");
                        System.exit(1);
                    }
                    System.out.printf("[attempt::%d] URI: %s, result: %d\n", attempt.get(), uri.get(), ping(uri.get()));
                    attempt.getAndAdd(1);
                }
            }, 0, interval * 1000);
        } catch (Exception ignored) {
            System.err.println("Interval value must be an integer");
        }
    }
}
