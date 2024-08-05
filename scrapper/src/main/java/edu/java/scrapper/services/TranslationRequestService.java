package edu.java.scrapper.services;

import edu.java.scrapper.clients.TranslateClinet;
import edu.java.scrapper.database.TranslationRequestRepository;
import edu.java.scrapper.dto.TranslationRequest;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationRequestService {

    private final TranslationRequestRepository translationRequestRepository;
    private final TranslateClinet translateClinet;

    public void addTranslation(TranslationRequest entity) {

        translationRequestRepository.add(entity);
    }

    public String getTranslation(String sourceLanguage, String targetLanguage, String text)
        throws ExecutionException, InterruptedException {
        return translateClinet.getTranslation(sourceLanguage, targetLanguage, text);
    }

}



