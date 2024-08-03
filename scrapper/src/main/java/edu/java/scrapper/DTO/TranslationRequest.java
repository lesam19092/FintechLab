package edu.java.scrapper.DTO;

import java.net.InetAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationRequest {
    private InetAddress ipAddress;
    private String inputString;
    private String TranslationRequest;
}
