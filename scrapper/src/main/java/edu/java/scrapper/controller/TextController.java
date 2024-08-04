package edu.java.scrapper.controller;

import edu.java.scrapper.clients.TranslateClinet;
import edu.java.scrapper.dto.LanguageCode;
import edu.java.scrapper.dto.TranslationRequest;
import edu.java.scrapper.services.TranslationRequestService;
import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class TextController {
    private final TranslateClinet translateClinet;
    private final TranslationRequestService translationRequestRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("languages", LanguageCode.values());
        return "main";
    }

    @GetMapping("/translated")
    public String add(
        @RequestParam("sourceLanguage") String sourceLanguage,
        @RequestParam("targetLanguage") String targetLanguage,
        @RequestParam("textForTranslation") String text,
        Model model,
        HttpServletRequest request
    ) throws JSONException {

        model.addAttribute("languages", LanguageCode.values());
        InetAddress clientIp = getClientIp(request);
        if (clientIp != null) {
            log.info("IP-адрес клиента: {}", clientIp.getHostAddress());
        } else {
            log.warn("Не удалось определить IP-адрес клиента.");
        }
        String translatedText = translateClinet.translate(sourceLanguage, targetLanguage, text);

        TranslationRequest translationRequest =
            new TranslationRequest(clientIp, text, translatedText);//TODO посмотреть , если запрос не выполнится
        translationRequestRepository.addTranslation(translationRequest);
        model.addAttribute("sourceText", text);
        model.addAttribute("translatedText", translatedText);
        return "main";
    }

    private InetAddress getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(remoteAddr);
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }

        return inetAddress;
    }

}
