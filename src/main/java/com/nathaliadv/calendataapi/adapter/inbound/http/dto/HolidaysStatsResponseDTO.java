package com.nathaliadv.calendataapi.adapter.inbound.http.dto;

import lombok.Builder;

@Builder
public record HolidaysStatsResponseDTO(
        String countryCode,
        long numberOfHolidays
) {
}
