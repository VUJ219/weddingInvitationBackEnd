package com.zsirosdeszkasok.wedding.controller;

import com.zsirosdeszkasok.wedding.service.FamilyService;
import com.zsirosdeszkasok.wedding.service.RegistrationService;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    private final FamilyService familyService;
    private final RegistrationService registrationService;

    public FamilyController(FamilyService familyService, RegistrationService registrationService) {
        this.familyService = familyService;
        this.registrationService = registrationService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FamilyDto getFamilyById(@PathVariable Integer id) {
        return familyService.getFamilyDtoById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyDto> getFamilies() {
        return familyService.getListOfAllFamilies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postFamily(@RequestBody FamilyDto familyDto) {
        registrationService.registerFamily(familyDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putFamily(@PathVariable Integer id, @RequestBody FamilyDto familyDto) {
        registrationService.updateFamilyById(id, familyDto);
    }
}
