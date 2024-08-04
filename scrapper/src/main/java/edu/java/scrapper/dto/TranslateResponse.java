package edu.java.scrapper.dto;

import java.util.List;
import lombok.Data;

@Data
public class TranslateResponse {

   private List<TranslatedWord> translations;

}
