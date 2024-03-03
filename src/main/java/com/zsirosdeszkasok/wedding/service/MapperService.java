package com.zsirosdeszkasok.wedding.service;

import com.zsirosdeszkasok.wedding.model.Family;
import com.zsirosdeszkasok.wedding.model.FamilyChange;
import com.zsirosdeszkasok.wedding.model.Person;
import com.zsirosdeszkasok.wedding.model.PersonChange;
import com.zsirosdeszkasok.wedding.service.dto.FamilyChangeDto;
import com.zsirosdeszkasok.wedding.service.dto.FamilyDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonChangeDto;
import com.zsirosdeszkasok.wedding.service.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public FamilyDto map(Family family) {
        return new FamilyDto(
                family.getId(), 
                family.getMembers().stream().map(this::map).toList(),
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
    public FamilyChangeDto map(FamilyChange familyChange) {
        return new FamilyChangeDto(
                familyChange.getPersonChanges().stream().map(personChange -> this.map(
                        personChange,
                        personChange.getPerson().getName()
                )).toList(),
                familyChange.getNewComment(),
                familyChange.getChangeTime()
        );
    }

    public PersonChangeDto map(PersonChange personChange, String name) {
        return new PersonChangeDto(
                name,
                personChange.getNewHasAccepted(),
                personChange.getWasCreated()
        );
    }
}
