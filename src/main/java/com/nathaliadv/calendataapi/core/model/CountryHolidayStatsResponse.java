package com.nathaliadv.calendataapi.core.model;

public record CountryHolidayStatsResponse(
        String countryCode,
        String countryName,
        int numberOfHolidays
) {
}
