package com.example.sonixbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.net.http.*;
import java.net.*;
import java.io.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Value("${gemini.api.key}")
    private String apiKey;

    @PostMapping("/ask")
    public String askAI(@RequestBody AiRequest req) throws Exception {

        String prompt = req.getMessage();

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();

        String json = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + prompt + "\" } ] } ] }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

class AiRequest {
    private String message;
    public String getMessage() { return message; }
    public void setMessage(String m) { this.message = m; }
}
