package com.TravelFactory.Translator.Dto.ApplicationDto;

import com.TravelFactory.Translator.Dto.TranslationKeysDto.TranslationKeysGetDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class ApplicationGetDto {
    private Long id;
    private String name;
    private Date createdAt;
}
