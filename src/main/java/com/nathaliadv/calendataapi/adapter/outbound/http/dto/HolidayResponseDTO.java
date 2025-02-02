package com.nathaliadv.calendataapi.adapter.outbound.http.dto;

import java.util.List;

public record HolidayResponseDTO(
        String date,
        String localName,
        String name,
        String countryCode,
        Boolean fixed,
        Boolean global,
        List<String> counties,
        Integer launchYear,
        List<String> types
) {
}
