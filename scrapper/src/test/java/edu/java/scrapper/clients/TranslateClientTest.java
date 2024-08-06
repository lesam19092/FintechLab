package edu.java.scrapper.clients;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TranslateClientTest {

    @Autowired
    private TranslateClient translateClinet;


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
