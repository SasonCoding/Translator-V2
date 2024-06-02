package com.TravelFactory.Translator.Service;

import com.TravelFactory.Translator.Dto.ApplicationDto.ApplicationGetDto;
import com.TravelFactory.Translator.Dto.ApplicationDto.ApplicationPostDto;
import com.TravelFactory.Translator.Dto.TranslationKeysDto.TranslationKeysGetDto;
import com.TravelFactory.Translator.Mapper.ApplicationMapper;
import com.TravelFactory.Translator.Mapper.TranslationKeysMapper;
import com.TravelFactory.Translator.Entities.Application;
import com.TravelFactory.Translator.Repository.ApplicationRepository;
import lombok.NoArgsConstructor;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private TranslationKeyService translationKeyServiceService;
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    TranslationKeysMapper translationKeysMapper;

    public List<ApplicationGetDto> getAllApplications() {
        List<Application> applicationList = StreamSupport
                .stream(applicationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return applicationMapper.applicationGetDtos(applicationList);
    }

    public ApplicationGetDto getApplicationById(Long id) {
        return applicationMapper.applicationGetDto(applicationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Application not found with id: " + id)));
    }

    public List<TranslationKeysGetDto> getTranslationkeysByAppId(Long appId) {
        return translationKeyServiceService.getTranslationKeyByAppId(appId);
    }

    public ApplicationGetDto saveApplication(ApplicationPostDto applicationDto) throws DataAccessException {
        try{
            Application.ApplicationBuilder applicationBuilder = Application.builder()
                    .name(applicationDto.getName());
            Application application = applicationBuilder.build();

            return applicationMapper.applicationGetDto(applicationRepository.save(application));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteApplication(Long id) { applicationRepository.deleteById(id); }

    public String generateCSV(Long appId) {
        if(appId == null) throw new IllegalArgumentException();

        Application application = applicationRepository.findById(appId).get();
        List<TranslationKeysGetDto> translationKeysDtosList = translationKeysMapper.translationKeysGetDtos((new ArrayList<>(application.getTranslationKeys())));
        JSONArray translationKeysJsonArray = new JSONArray(translationKeysDtosList);

        try {
            return CDL.toString(translationKeysJsonArray);
        }
        catch (Exception e) {
            throw e;
        }
    }
}
