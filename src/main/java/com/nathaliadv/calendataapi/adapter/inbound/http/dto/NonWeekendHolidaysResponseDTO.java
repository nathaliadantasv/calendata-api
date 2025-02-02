package com.nathaliadv.calendataapi.adapter.inbound.http.dto;

import com.nathaliadv.calendataapi.core.model.CountryHolidayStatsResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record NonWeekendHolidaysResponseDTO(
        List<HolidaysStatsResponseDTO> nonWeekendHolidays
) {

    public static NonWeekendHolidaysResponseDTO from(List<CountryHolidayStatsResponse> countryHolidayStatsResponses) {
        List<HolidaysStatsResponseDTO> nonWeekendHolidays = countryHolidayStatsResponses.stream()
                .map(countryHolidayStatsResponse -> HolidaysStatsResponseDTO.builder()
                        .countryCode(countryHolidayStatsResponse.countryCode())
                        .numberOfHolidays(countryHolidayStatsResponse.numberOfHolidays())
                        .build())
                .toList();
        return new NonWeekendHolidaysResponseDTO(nonWeekendHolidays);
    }
}
