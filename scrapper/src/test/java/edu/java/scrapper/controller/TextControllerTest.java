package edu.java.scrapper.controller;

import edu.java.scrapper.clients.TranslateClient;
import edu.java.scrapper.services.TranslationRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TextControllerTest {

    @Mock
    private TranslateClient translateClinet;

    @Mock
    private TranslationRequestService translationRequestRepository;

    private TextController textController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        textController = new TextController(translationRequestRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(textController).build();
    }

    @Test
    public void testAddMethod_Success() throws Exception {
        String sourceLanguage = "en";
        String targetLanguage = "ru";
        String text = "Hello World";
        String translatedText = "Привет мир";

        when(translateClinet.translate(sourceLanguage, targetLanguage, text)).thenReturn(translatedText);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/translated")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("sourceLanguage", sourceLanguage)
                .param("targetLanguage", targetLanguage)
                .param("textForTranslation", text))
            .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status, "The request should return status 200 (OK)");

    }

}
