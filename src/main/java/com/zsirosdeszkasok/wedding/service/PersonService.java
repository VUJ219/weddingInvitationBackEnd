package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.Family;
import com.zsirosdeszkasok.wedding.model.Person;
import com.zsirosdeszkasok.wedding.model.PersonRepository;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final MapperService mapperService;

    public PersonService(PersonRepository personRepository, FamilyService familyService, MapperService mapperService) {
        this.personRepository = personRepository;
        this.mapperService = mapperService;
    }

    public PersonDto getPersonById(Integer id) {
        return mapperService.map(personRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Person found with id: " + id))
        );
    }
    public List<PersonDto> getListOfAllPeople() {
        return personRepository.findAll().stream().map(mapperService::map).collect(Collectors.toList());
    }

    public void savePerson(PersonDto personDto, Family family) {
        personRepository.save(mapperService.map(personDto, family));
    }

    public Person updatePerson(Integer id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setHasAccepted(personDto.hasAccepted());
        return personRepository.save(person);
    }

    public void saveMembers(List<PersonDto> members, Family family) {
        personRepository.saveAll(members.stream()
                .map(member -> mapperService.map(member, family)).toList());
    }

    public void updateMembers(Family family, List<PersonDto> memberDtos) {
        List<Person> members = personRepository.findAllByFamilyId(family.getId());

        memberDtos.forEach(personDto -> {
            boolean isSavesAlready = false;
            Person currentPerson = null;
            if (personDto.id() != null) {
                for (Person person : members) {
                    if (Objects.equals(person.getId(), personDto.id())) {
                        isSavesAlready = true;
                        currentPerson = person;
                        break;
                    }
                }
            }
            if (isSavesAlready) {
                currentPerson.setHasAccepted(personDto.hasAccepted());
            } else {
                currentPerson = new Person(
                        personDto.name(),
                        family,
                        personDto.hasAccepted()
                );
                members.add(currentPerson);
            }
            personRepository.saveAll(members);
        });
    }
}
