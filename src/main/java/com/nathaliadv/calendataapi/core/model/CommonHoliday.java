package com.nathaliadv.calendataapi.core.model;

import java.time.LocalDate;

public record CommonHoliday(
        LocalDate date,
        String localNameToFirstCountry,
        String localNameToSecondCountry
) {
}
