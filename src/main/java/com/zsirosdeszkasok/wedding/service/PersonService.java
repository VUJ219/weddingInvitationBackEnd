package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.*;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonChangeRepository personChangeRepository;
    private final MapperService mapperService;

    public PersonService(PersonRepository personRepository, FamilyService familyService, PersonChangeRepository personChangeRepository, MapperService mapperService) {
        this.personRepository = personRepository;
        this.personChangeRepository = personChangeRepository;
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
        Person person = personRepository.save(mapperService.map(personDto, family));
        personChangeRepository.save(new PersonChange(
                null,
                person,
                person.getFamily(),
                Instant.now(),
                person.getHasAccepted(),
                true
        ));
    }

    public Person updatePerson(Integer id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setHasAccepted(personDto.hasAccepted());
        personChangeRepository.save(new PersonChange(
                null,
                person,
                person.getFamily(),
                Instant.now(),
                personDto.hasAccepted(),
                false
        ));
        return personRepository.save(person);
    }

    public void saveMembers(List<PersonDto> members, Family family) {
        personRepository.saveAll(members.stream()
                .map(member -> mapperService.map(member, family)).toList());
    }

    public void updateMembers(Family family, List<PersonDto> memberDtos, FamilyChange familyChange) {
        List<Person> members = personRepository.findAllByFamilyId(family.getId());
        Set<Integer> oldMemberIds = new HashSet<>();
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
                oldMemberIds.add(currentPerson.getId());
            } else {
                currentPerson = new Person(
                        personDto.name(),
                        family,
                        personDto.hasAccepted()
                );
                members.add(currentPerson);
            }
        });
        List<Person> savedMembers = personRepository.saveAll(members);
        personChangeRepository.saveAll(savedMembers.stream().map(member -> {
            return new PersonChange(
                familyChange,
                member,
                family,
                Instant.now(),
                member.getHasAccepted(),
                !oldMemberIds.contains(member.getId())
            );
        }).collect(Collectors.toList()));
    }
}
