package com.TravelFactory.Translator.Dto.TranslationKeysDto;

import com.TravelFactory.Translator.Dto.TranslationDto.TranslationSummaryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class TranslationKeysGetDto {
    private Long id;

    @NotNull
    private String key;

    @NotEmpty
    @ToString.Exclude
    @JsonProperty("translations")
    private List<TranslationSummaryDto> translationSummaryDtos;

    private Date createdAt;
}
