package edu.java.scrapper;

import edu.java.scrapper.clients.TranslateClinet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TranslateClientTest {

    @Autowired
    private TranslateClinet translateClinet;


    @Test
    public void testTranslate_Success() throws Exception {
        String sourceLanguage = "en";
        String targetLanguage = "ru";
        String text = "Hello world";
        String translatedText = "Привет, мир";

        String translation = translateClinet.translate(sourceLanguage, targetLanguage, text);

        System.out.println(translation);

        assertEquals(translatedText, translation, "Translated text should match");

    }

}
