package edu.java.scrapper.DATABASE;

import edu.java.scrapper.DTO.TranslationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class TranslationRequestService implements TranslationRequestRepository {

    private final JdbcClient jdbcClient;

    @Override
    @Transactional
    public void add(TranslationRequest request) {
        String sql =
            "INSERT INTO requests (ip_address, input_text, translated_text) "
                + "VALUES (:ip_address,:input_text,:translated_text)";

        jdbcClient.sql(sql)
            .param("ip_address", request.getIpAddress().toString())
            .param("input_text", request.getInputString())
            .param("translated_text", request.getTranslatedString())
            .update();
    }
}

