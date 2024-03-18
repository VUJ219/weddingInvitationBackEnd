package com.zsirosdeszkasok.wedding.controller;

import com.zsirosdeszkasok.wedding.service.PersonService;
import com.zsirosdeszkasok.wedding.service.RegistrationService;
import com.zsirosdeszkasok.wedding.service.dto.PeopleMetadataDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;
    private final RegistrationService registrationService;
    //private final Logger logger;

    public PersonController(PersonService personService, RegistrationService registrationService) {
        this.personService = personService;
        this.registrationService = registrationService;
        //this.logger = logger;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable Integer id) {
        //logger.log(Level.INFO, "getPersonById called");
        return personService.getPersonById(id);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPeople() {
        return personService.getListOfAllPeople();
    }
    @GetMapping(value = "/metadata")
    @ResponseStatus(HttpStatus.OK)
    public PeopleMetadataDto getPeopleMetadata() {
        return personService.getPeopleMetadata();
    }

    @GetMapping(value = "/accepted")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPeopleWhoAccepted() {
        return personService.getPeopleByHasAccepted(true);
    }

    @GetMapping(value = "/declined")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPeoplesWhoDeclined() {
        return personService.getPeopleByHasAccepted(false);
    }

    @GetMapping(value = "/no-response")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPeoplesWhoDidNotRespond() {
        return personService.getPeopleByHasAccepted(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody PersonDto personDto) {
        registrationService.registerPerson(personDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putPerson(@PathVariable Integer id, @RequestBody PersonDto personDto) {
        registrationService.updatePersonById(id, personDto);
    }
}
