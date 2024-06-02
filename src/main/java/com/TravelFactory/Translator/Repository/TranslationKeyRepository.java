package com.TravelFactory.Translator.Repository;

import com.TravelFactory.Translator.Entities.TranslationKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationKeyRepository extends CrudRepository<TranslationKey, Long> {
    List<TranslationKey> findByApplicationId(Long appId);
}