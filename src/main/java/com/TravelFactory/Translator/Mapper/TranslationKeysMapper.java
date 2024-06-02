package com.TravelFactory.Translator.Mapper;

import com.TravelFactory.Translator.Dto.TranslationKeysDto.TranslationKeysGetDto;
import com.TravelFactory.Translator.Entities.TranslationKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TranslationKeysMapper {
    TranslationKeysMapper INSTANCE = Mappers.getMapper(TranslationKeysMapper.class);
    @Mapping(source = "translations", target = "translationSummaryDtos")
    TranslationKeysGetDto translationKeysGetDto(TranslationKey translationKey);
    @Mapping(source = "translations", target = "translationSummaryDtos")
    List<TranslationKeysGetDto> translationKeysGetDtos(List<TranslationKey> translationKeys);
}
