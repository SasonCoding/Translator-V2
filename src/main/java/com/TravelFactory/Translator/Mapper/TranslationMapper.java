package com.TravelFactory.Translator.Mapper;

import com.TravelFactory.Translator.Dto.TranslationDto.TranslationPostDto;
import com.TravelFactory.Translator.Dto.TranslationDto.TranslationSummaryDto;
import com.TravelFactory.Translator.Dto.TranslationKeysDto.TranslationKeysGetDto;
import com.TravelFactory.Translator.Entities.Translation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TranslationMapper {
    TranslationMapper INSTANCE = Mappers.getMapper(TranslationMapper.class);
    List<TranslationKeysGetDto> translationGetDtos(List<Translation> translationList);
    Translation toTranslationByTranslationSummary(TranslationSummaryDto translationSummaryDto);
}
