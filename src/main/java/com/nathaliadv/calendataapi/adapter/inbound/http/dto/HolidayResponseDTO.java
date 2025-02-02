package com.nathaliadv.calendataapi.adapter.inbound.http.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record HolidayResponseDTO(
        LocalDate date,
        String name
) {
}
