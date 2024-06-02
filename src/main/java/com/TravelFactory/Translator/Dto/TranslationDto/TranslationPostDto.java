package com.TravelFactory.Translator.Dto.TranslationDto;

import com.TravelFactory.Translator.Entities.Translation;
import lombok.Data;

@Data
public class TranslationPostDto {
    private Long translationKey;
    private String languageCode;
    private String text;
}
