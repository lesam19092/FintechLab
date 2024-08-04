package edu.java.scrapper.DATABASE;

import edu.java.scrapper.DTO.TranslationRequest;
import java.net.UnknownHostException;

public interface TranslationRequestRepository {


    void add(TranslationRequest request) throws UnknownHostException;
}
