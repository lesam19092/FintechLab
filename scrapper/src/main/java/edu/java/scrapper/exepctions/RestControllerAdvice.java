
package edu.java.scrapper.exepctions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(HttpServerErrorException.class)

    public String handle(HttpServerErrorException ex) {

        return "main";
        //   System.out.println("ПЫТАЕМСЯ СЛОВИТЬ ЭКСПЕШН 500");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public void handle(HttpClientErrorException ex) {

        System.out.println("http 400 Ошибка доступа к ресурсу перевода");
    }
    //TODO refactor
}

