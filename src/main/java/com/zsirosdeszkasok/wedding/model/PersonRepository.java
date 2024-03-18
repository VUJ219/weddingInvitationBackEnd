package com.zsirosdeszkasok.wedding.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllByFamilyId(Integer familyId);
    List<Person> findAllByHasAccepted(Boolean hasAccepted);
}
