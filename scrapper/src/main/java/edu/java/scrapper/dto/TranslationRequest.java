package edu.java.scrapper.dto;

import java.net.InetAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TranslationRequest {
    private InetAddress ipAddress;
    private String inputString;
    private String translatedString;

    public TranslationRequest(InetAddress ipAddress, String inputString, String translatedString) {
        this.ipAddress = ipAddress;
        this.inputString = inputString;
        this.translatedString = translatedString;
    }
}
