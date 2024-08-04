package edu.java.scrapper.services;

import edu.java.scrapper.clients.TranslateClinet;
import edu.java.scrapper.database.TranslationRequestRepository;
import edu.java.scrapper.dto.TranslationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationRequestService {

    private final TranslationRequestRepository translationRequestRepository;
    private final TranslateClinet translateClinet;
//TODO ДОБАВИТЬ СЮДА ПЕРЕВОД
    public void addTranslation(TranslationRequest entity) {

        translationRequestRepository.add(entity);
    }

}

