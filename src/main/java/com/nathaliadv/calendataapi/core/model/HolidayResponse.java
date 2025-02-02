package com.nathaliadv.calendataapi.core.model;

import com.nathaliadv.calendataapi.adapter.outbound.http.dto.HolidayResponseDTO;
import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
public record HolidayResponse(
        LocalDate date,
        String localName,
        String name
) {

    public static HolidayResponse from(HolidayResponseDTO responseDTO) {
        return HolidayResponse.builder()
                .date(convertToLocalDate(responseDTO.date()))
                .localName(responseDTO.localName())
                .name(responseDTO.name())
                .build();
    }

    public boolean isWeekend() {
        return this.date.getDayOfWeek() == DayOfWeek.SATURDAY || this.date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
