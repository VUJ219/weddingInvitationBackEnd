package com.zsirosdeszkasok.wedding.service.dto;

public record PersonChangeDto(
        String name,
        Boolean newHasAccepted,
        Boolean wasCreated
) { }
