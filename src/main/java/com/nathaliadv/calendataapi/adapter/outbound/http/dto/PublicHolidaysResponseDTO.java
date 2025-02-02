package com.nathaliadv.calendataapi.adapter.outbound.http.dto;

import java.util.List;

public record PublicHolidaysResponseDTO(
        List<HolidayResponseDTO> holidays
) {
}
