package edu.java.scrapper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TextController {

    /*@RequestMapping("/text")
    public String translateText(
        Model model
    ) {
        {
            System.out.println(model.toString());
            return "main.html";
        }
    }*/

    @RequestMapping("/")
    public String home(Model m) {
        return "main";
    }

    @GetMapping("/translated")
    public String add(@RequestParam("textForTranslation") String text, Model model) {
        System.out.println(text); // Проверка, что значение текста корректно получено
        model.addAttribute("translatedText", text); // Добавляем значение в модель под ключом "message"
        return "main"; // Возвращаем имя шаблона без расширения (.html)
    }
}
