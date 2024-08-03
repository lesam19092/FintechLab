package edu.java.scrapper.DATABASE;

import edu.java.scrapper.DTO.TranslationRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JdbcTranslationRequestService implements TranslationRequestRepository {

    private final TranslationRequestRepository translationRequestRepository;

    public void add(TranslationRequest chat) {
        translationRequestRepository.add(chat);
    }
}
