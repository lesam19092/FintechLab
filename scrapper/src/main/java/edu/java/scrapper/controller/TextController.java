package edu.java.scrapper.controller;

import edu.java.scrapper.dto.LanguageCode;
import edu.java.scrapper.dto.TranslationRequest;
import edu.java.scrapper.services.TranslationRequestService;
import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Controller
@AllArgsConstructor
@Slf4j
public class TextController {

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
    ) throws ExecutionException, InterruptedException {




        model.addAttribute("languages", LanguageCode.values());

        if (sourceLanguage.equals(targetLanguage)) {

            log.info("Исходный и целевой языки не могут быть одинаковыми");
            return "redirect:/error";
        }


        InetAddress clientIp = getClientIp(request);
        if (clientIp != null) {
            log.info("IP-адрес клиента: {}", clientIp.getHostAddress());
        } else {
            log.warn("Не удалось определить IP-адрес клиента.");
        }
        String translatedText = translationRequestRepository.getTranslation(sourceLanguage, targetLanguage, text);

        TranslationRequest translationRequest =
            new TranslationRequest(clientIp, text, translatedText);
        translationRequestRepository.addTranslation(translationRequest);
        model.addAttribute("sourceText", text);
        model.addAttribute("translatedText", translatedText);
        return "main";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(
        HttpClientErrorException
            exception
    ) {

        if (exception.getStatusCode().value() == 400) {
            log.info("http 400 текст не может быть пустым");
        }
        if (exception.getStatusCode().value() == 401) {
            log.info("htto 401 не авторизирован");
        }
        return "redirect:/error";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public String handleServerError(HttpServerErrorException exception) {

        log.info(exception.getMessage());

        return "redirect:/error";
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
