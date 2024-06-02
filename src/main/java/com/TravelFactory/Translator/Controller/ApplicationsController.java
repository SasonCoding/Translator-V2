package com.TravelFactory.Translator.Controller;

import com.TravelFactory.Translator.Dto.ApplicationDto.ApplicationGetDto;
import com.TravelFactory.Translator.Dto.ApplicationDto.ApplicationPostDto;
import com.TravelFactory.Translator.Dto.TranslationKeysDto.TranslationKeysGetDto;
import com.TravelFactory.Translator.Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationGetDto>> findApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApplicationGetDto> findApplication(@PathVariable("id") Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @RequestMapping(value = "/translationkeys/{appId}", method = RequestMethod.GET)
    public ResponseEntity<List<TranslationKeysGetDto>> findTranslationKeysByAppId(@PathVariable("appId") Long id) {
        return ResponseEntity.ok(applicationService.getTranslationkeysByAppId(id));
    }

    @RequestMapping(value = "/download/{appId}", method = RequestMethod.GET)
    public ResponseEntity<Object> createApplication(@PathVariable Long appId) {
        String translationCSV = applicationService.generateCSV(appId);
        byte[] byteArray = translationCSV.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + "translator" + "_" + appId + ".csv");

        return ResponseEntity.status( HttpStatus.OK).headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ApplicationGetDto> createApplication(@RequestBody ApplicationPostDto applicationDto) {
        return ResponseEntity.ok(applicationService.saveApplication(applicationDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteApplication(@PathVariable("id") Long id) {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>("Application with the id: " + id + " was successfully deleted", HttpStatus.OK);
    }
}