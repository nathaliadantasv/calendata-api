package com.nathaliadv.calendataapi.core.model;

import lombok.Builder;

import java.util.List;

@Builder
public record CountryHolidayStatsResponse(
        String countryCode,
        long numberOfHolidays
) {

    public static CountryHolidayStatsResponse from(List<HolidayResponse> holidaysInTheYear, String countryCode) {
        if (holidaysInTheYear == null || holidaysInTheYear.isEmpty()) {
            return null;
        }
        long numberOfHolidays = holidaysInTheYear.stream()
                .filter(holiday -> !holiday.isWeekend())
                .count();

        return CountryHolidayStatsResponse.builder()
                .countryCode(countryCode)
                .numberOfHolidays(numberOfHolidays)
                .build();
    }
}
