package edu.java.scrapper.clients;

import edu.java.scrapper.configuration.ApplicationConfig;
import edu.java.scrapper.dto.TranslateResponse;
import edu.java.scrapper.dto.TranslatedWord;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
public class TranslateClinet {

    private final ApplicationConfig applicationConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    public TranslateClinet(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public String translate(String sourceLanguage, String targetLanguage, String text)
        throws JSONException {

        JSONObject requestParams = new JSONObject();
        requestParams.put("folderId", applicationConfig.folderId());
        requestParams.put("sourceLanguageCode", sourceLanguage);
        requestParams.put("targetLanguageCode", targetLanguage);
        requestParams.put("texts", text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + applicationConfig.iamToken());

        HttpEntity<String> entity = new HttpEntity<>(requestParams.toString(), headers);

        TranslateResponse translatedText =
            restTemplate.postForObject(applicationConfig.yaUrl(), entity, TranslateResponse.class);

        return translatedText.getTranslations().stream()
            .map(TranslatedWord::getText)
            .collect(Collectors.joining(" "));

    }
}

