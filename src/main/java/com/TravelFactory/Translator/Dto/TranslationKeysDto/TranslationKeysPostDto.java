package com.TravelFactory.Translator.Dto.TranslationKeysDto;

import com.TravelFactory.Translator.Dto.TranslationDto.TranslationSummaryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TranslationKeysPostDto {
    @NotNull
    private String key;
    @NotEmpty
    @JsonProperty("translations")
    private List<TranslationSummaryDto> translationSummaryDtos;
}
