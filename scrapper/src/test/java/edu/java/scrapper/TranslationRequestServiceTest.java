package edu.java.scrapper;

import edu.java.scrapper.DATABASE.TranslationRequestService;
import edu.java.scrapper.DTO.TranslationRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TranslationRequestServiceTest {

    @Mock
    private TranslationRequestService translationRequestService;

    @Test
    public void testAddRequestRepositoryOnSuccess() throws UnknownHostException {
        TranslationRequest request = new TranslationRequest(InetAddress.getByName("0:0:0:0:0:0:0:1"), "привет", "hi");
        Mockito.doNothing().when(translationRequestService).add(request);
        translationRequestService.add(request);
        Mockito.verify(translationRequestService).add(request);
    }

}
