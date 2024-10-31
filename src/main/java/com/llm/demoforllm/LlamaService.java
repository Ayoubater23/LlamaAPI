package com.llm.demoforllm;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class LlamaService {

    private final String llamaServerUrl = "http://localhost:11434/api/generate";

    public String generateResponse(String prompt) {
        // URL connection for streaming response
        try {
            URL url = new URL(llamaServerUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Create request body
            String requestBody = String.format("{\"model\": \"llama3.1:latest\", \"prompt\": \"%s\"}", prompt);
            connection.setDoOutput(true);
            connection.getOutputStream().write(requestBody.getBytes());

            // Buffer to read streamed response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder fullResponseText = new StringBuilder();

            // Read the streamed lines
            while ((inputLine = in.readLine()) != null) {
                // Parse each line (assuming each is a valid JSON object)
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseNode = objectMapper.readTree(inputLine);
                String responsePart = responseNode.path("response").asText();
                fullResponseText.append(responsePart);  // Append to the full response
            }

            in.close();  // Close the stream
            return fullResponseText.toString();  // Return the complete response

        } catch (IOException e) {
            e.printStackTrace();
            return "Error in generating response";
        }
    }
}
