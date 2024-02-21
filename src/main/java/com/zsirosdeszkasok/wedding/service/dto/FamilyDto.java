package com.zsirosdeszkasok.wedding.service.dto;

import java.util.List;

public record FamilyDto(Integer id, List<PersonDto> members, String comment) {
}
