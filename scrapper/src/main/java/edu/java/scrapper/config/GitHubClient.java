package edu.java.scrapper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class GitHubClient {
    private WebClient webClient;

    // @Value("${app.yaUrl}")
    private String baseUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    private String IAM_TOKEN = System.getenv("IAM_TOKEN");
    private String folderId = System.getenv("folderId");
    private final WebClient.Builder webClientBuilder = WebClient.builder();

    public GitHubClient() {
        System.out.println(baseUrl);
        webClient = webClientBuilder.baseUrl(baseUrl)                   //get ENV
            .defaultHeaders(h -> h.setBearerAuth(IAM_TOKEN))
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public String translate(String srcLanguage, String trgtLanguage, String word) {

        String url = baseUrl
            + "?folderId=" + folderId
            + "&sourceLanguageCode=" + srcLanguage
            + "&targetLanguageCode=" + trgtLanguage
            + "&texts=" + word;

        return webClient.post()
            .uri(url)
            .retrieve()
            .bodyToMono(String.class)
            .block();

    }

}
