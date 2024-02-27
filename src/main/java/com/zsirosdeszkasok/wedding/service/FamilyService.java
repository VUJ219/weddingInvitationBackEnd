package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.*;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final MapperService mapperService;

    public FamilyService(FamilyRepository familyRepository, MapperService mapperService) {
        this.familyRepository = familyRepository;
        this.mapperService = mapperService;
    }

    public FamilyDto getFamilyDtoById(Integer id) {
        return mapperService.map(familyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Family found with id: " + id))
        );
    }
    public Family getFamilyById(Integer id) {
        return familyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Family found with id: " + id));
    }
    public List<FamilyDto> getListOfAllFamilies() {
        return familyRepository.findAll().stream()
                .map(mapperService::map).toList();
    }

    public Family saveFamily(FamilyDto familyDto) {
        return familyRepository.save(new Family(
                Collections.emptyList(),
                familyDto.comment(),
                null
        ));
    }

    public Family updateFamilyById(Integer id, FamilyDto familyDto) {
        Family family = familyRepository.findById(id).orElseThrow();
        family.setComment(familyDto.comment());
        return familyRepository.save(family);
    }
}
