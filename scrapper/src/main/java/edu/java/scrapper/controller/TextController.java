package edu.java.scrapper.controller;

import edu.java.scrapper.DATABASE.TranslationRequestRepository;
import edu.java.scrapper.DTO.LanguageCode;
import edu.java.scrapper.DTO.TranslationRequest;
import edu.java.scrapper.config.TranslateClinet;
import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TextController {
    private final TranslateClinet translateClinet;

    private final TranslationRequestRepository translationRequestRepository;

    private static final Logger logger = LoggerFactory.getLogger(TextController.class.getName());

    public TextController(
        TranslateClinet translateController,
        TranslationRequestRepository translationRequestRepository
    ) {
        this.translateClinet = translateController;
        this.translationRequestRepository = translationRequestRepository;
    }

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
    ) throws JSONException, UnknownHostException {

        model.addAttribute("languages", LanguageCode.values());
        InetAddress clientIp = getClientIp(request);
        if (clientIp != null) {
            logger.info("IP-адрес клиента: {}", clientIp.getHostAddress());
        } else {
            logger.warn("Не удалось определить IP-адрес клиента.");
        }
        String translatedText = translateClinet.translate(sourceLanguage, targetLanguage, text);

        TranslationRequest translationRequest =
            new TranslationRequest(clientIp, text, translatedText);//TODO посмотреть , если запрос не выполнится
        translationRequestRepository.add(translationRequest);
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
            logger.error(e.getMessage());
        }

        return inetAddress;
    }

}
