package edu.java.scrapper.configuration;

import edu.java.scrapper.exepction.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration

public class RestTemplateConfig {

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler())
            .build();
    }
}
