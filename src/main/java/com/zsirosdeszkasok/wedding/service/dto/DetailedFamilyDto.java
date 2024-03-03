package com.zsirosdeszkasok.wedding.service.dto;

import java.util.List;

public record DetailedFamilyDto(
        Integer id,
        List<PersonDto> allMembers,
        String currentComment,
        List<FamilyChangeDto> allChanges
) { }
