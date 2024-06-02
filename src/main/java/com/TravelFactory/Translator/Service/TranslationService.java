package com.TravelFactory.Translator.Service;

import com.TravelFactory.Translator.Dto.TranslationKeysDto.TranslationKeysGetDto;
import com.TravelFactory.Translator.Mapper.TranslationMapper;
import com.TravelFactory.Translator.Entities.Translation;
import com.TravelFactory.Translator.Repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TranslationService {
    @Autowired
    private TranslationRepository translationRepository;
    public List<Translation> saveTranslations(List<Translation> translations) {
        return (List<Translation>) translationRepository.saveAll(translations);
    }
}
