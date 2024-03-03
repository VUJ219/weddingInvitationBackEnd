package com.zsirosdeszkasok.wedding.service.dto;

import java.time.Instant;
import java.util.List;

public record FamilyChangeDto(
        List<PersonChangeDto> personChanges,
        String newComment,
        Instant changeTime
) { }
