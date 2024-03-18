package com.zsirosdeszkasok.wedding.service.dto;

public record PeopleMetadataDto(
        int sum,
        int didNotFillOutSum,
        FilledOutData filledOut
) {
    public record FilledOutData(
            int sum,
            SubData accepted,
            SubData declined
    ) {
        public record SubData(
                int sum,
                int originalPeople,
                int addedPeople
        ) {}
    }
}
