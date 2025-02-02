package com.nathaliadv.calendataapi.core.model;

import lombok.Builder;

import java.util.List;

@Builder
public record CommonHolidaysResponse(
        String firstCountry,
        String secondCountry,
        List<CommonHoliday> commonHolidays
) { }
