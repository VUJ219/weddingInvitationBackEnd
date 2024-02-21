package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.Family;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PersonService personService;
    private final FamilyService familyService;

    public RegistrationService(PersonService personService, FamilyService familyService) {
        this.personService = personService;
        this.familyService = familyService;
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
        personService.updateMembers(family, familyDto.members());
    }

    public void registerFamily(FamilyDto familyDto) {
        Family family = familyService.saveFamily(familyDto);
        personService.saveMembers(familyDto.members(), family);
    }
}
