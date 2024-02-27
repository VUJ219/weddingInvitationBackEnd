package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.*;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RegistrationService {

    private final PersonService personService;
    private final FamilyService familyService;
    private final FamilyChangeRepository familyChangeRepository;

    public RegistrationService(PersonService personService, FamilyService familyService, FamilyChangeRepository familyChangeRepository) {
        this.personService = personService;
        this.familyService = familyService;
        this.familyChangeRepository = familyChangeRepository;
    }

    public void registerPerson(PersonDto personDto) {
        Family family = familyService.getFamilyById(personDto.familyId());
        personService.savePerson(personDto, family);
    }

    public void updatePersonById(Integer id, PersonDto personDto) {
        personService.updatePerson(id, personDto);
    }

    public void updateFamilyById(Integer id, FamilyDto familyDto) {
        Family family = familyService.updateFamilyById(id, familyDto);
        FamilyChange familyChange = familyChangeRepository.save(new FamilyChange(
                family, null, familyDto.comment(), Instant.now()
        ));
        personService.updateMembers(family, familyDto.members(), familyChange);
    }

    public void registerFamily(FamilyDto familyDto) {
        Family family = familyService.saveFamily(familyDto);
        personService.saveMembers(familyDto.members(), family);
    }
}
