package com.zsirosdeszkasok.wedding.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyChangeRepository extends JpaRepository<FamilyChange, Integer> {
    public List<FamilyChange> findAllByFamilyId(Integer familyId);
}
