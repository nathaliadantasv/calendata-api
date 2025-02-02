package com.nathaliadv.calendataapi.adapter.inbound.http.dto;

import com.nathaliadv.calendataapi.core.model.CommonHoliday;
import com.nathaliadv.calendataapi.core.model.CommonHolidaysResponse;

import java.util.List;

public record CommonHolidaysResponseDTO(
        String firstCountry,
        String secondCountry,
        List<CommonHolidayNamesResponseDTO> commonHolidays
) {

    public static CommonHolidaysResponseDTO from(CommonHolidaysResponse commonHolidays) {
        List<CommonHoliday> listOfCommonHolidays = commonHolidays.commonHolidays();
        List<CommonHolidayNamesResponseDTO> commonHolidayNamesResponseDTOS = listOfCommonHolidays.stream()
                .map(commonHoliday -> CommonHolidayNamesResponseDTO.builder()
                        .date(commonHoliday.date())
                        .localNameToFirstCountry(commonHoliday.localNameToFirstCountry())
                        .localNameToSecondCountry(commonHoliday.localNameToSecondCountry())
                        .build())
                .toList();
        return new CommonHolidaysResponseDTO(
                commonHolidays.firstCountry(),
                commonHolidays.secondCountry(),
                commonHolidayNamesResponseDTOS
        );
    }
}
