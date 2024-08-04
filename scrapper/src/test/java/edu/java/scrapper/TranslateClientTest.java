package edu.java.scrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.java.scrapper.config.TranslateClinet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TranslateClientTest {

    @Mock
    private TranslateClinet translateClinet;

    @Autowired
    private TestRestTemplate restTemplate;


    //TODO ДОПИСАТЬ ТЕСТ

    @Test
    public void testTranslate_Success() throws Exception {
        String sourceLanguage = "en";
        String targetLanguage = "ru";
        String text = "Hello World";
        String translatedText = "Привет мир";

        String responseBody = "{\"translations\":[{\"text\":\"" + translatedText + "\"}]}";
        ResponseEntity<String> response = new ResponseEntity<>(responseBody, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class),
            any(HttpMethod.class), any(HttpEntity.class), any(Class.class)
        )).thenReturn(response);

        String translation = translateClinet.translate(sourceLanguage, targetLanguage, text);

        System.out.println(translation);

        assertEquals(translatedText, translation, "Translated text should match");

    }

}
