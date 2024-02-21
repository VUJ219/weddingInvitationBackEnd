package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.Family;
import com.zsirosdeszkasok.wedding.model.Person;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MapperService {

    public FamilyDto map(Family family) {
        return new FamilyDto(
                family.getId(), 
                family.getMembers().stream().map(this::map).collect(Collectors.toList()),
                family.getComment()
        );
    }
    public PersonDto map(final Person person) {
        return new PersonDto(
                person.getId(),
                person.getName(),
                person.getFamily().getId(),
                person.getHasAccepted()
        );
    }
    public Person map(final PersonDto personDto, Family family) {
        return new Person(
                personDto.name(),
                family,
                personDto.hasAccepted()
        );
    }
}
