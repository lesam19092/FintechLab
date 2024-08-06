package edu.java.scrapper.clients;

import edu.java.scrapper.configuration.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TranslateClientTest {

    @Autowired
    private TranslateClinet translateClinet;


    @Test
    public void testTranslate_Unauthorized() {
        String sourceLanguage = "en";
        String targetLanguage = "ru";
        String text = "Hello world";

        assertThrows(HttpClientErrorException.Unauthorized.class, () -> {
            translateClinet.translate(sourceLanguage, targetLanguage, text);
        });
    }

}
