package com.nathaliadv.calendataapi.adapter.inbound.http.dto;

import com.nathaliadv.calendataapi.core.model.HolidayResponse;

import java.util.List;
import java.util.stream.Collectors;

public record LastHolidaysResponseDTO(
        List<HolidayResponseDTO> holidays
) {

    public static LastHolidaysResponseDTO from(List<HolidayResponse> holidays) {
        List<HolidayResponseDTO> holidayDTOs = holidays.stream()
                .map(holiday -> HolidayResponseDTO.builder()
                        .date(holiday.date())
                        .name(holiday.name())
                        .build())
                .collect(Collectors.toList());

        return new LastHolidaysResponseDTO(holidayDTOs);
    }
}
