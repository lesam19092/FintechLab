package edu.java.scrapper.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class TranslateClinet {

    @Value("${app.yaUrl}")
    private String baseUrl;
    private final String IAM_TOKEN = System.getenv("IAM_TOKEN");
    private final String folderId = System.getenv("folderId");
    private final RestTemplate restTemplate = new RestTemplate();

    public String translate(String sourceLanguage, String targetLanguage, String text)
        throws JSONException {

        JSONObject requestParams = new JSONObject();
        requestParams.put("folderId", folderId);
        requestParams.put("sourceLanguageCode", sourceLanguage);
        requestParams.put("targetLanguageCode", targetLanguage);
        requestParams.put("texts", text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + IAM_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(requestParams.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);


        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                return root.path("translations").get(0).path("text").asText();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse translation response", e);
            }
        } else {
            throw new RuntimeException("Translation request failed: " + response.getStatusCode());
        }
    }

}
