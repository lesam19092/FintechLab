package edu.java.scrapper.DATABASE;

import edu.java.scrapper.DTO.TranslationRequest;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class TranslationRequestRepositoryImpl implements TranslationRequestRepository {

    private final JdbcClient jdbcClient;
    private final Logger logger = Logger.getLogger(TranslationRequestRepositoryImpl.class.getName());

    @Override
    @Transactional
    public void add(TranslationRequest request) {
        /*String sql =
            "insert into chat(chat_id) values(:chatId)";

        jdbcClient.sql(sql)
            .param("chatId", chatEntity.getChatId())
            .update();
    }*/
    }
}
