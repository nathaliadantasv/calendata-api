package com.nathaliadv.calendataapi.adapter.outbound.http.dto;

import lombok.Data;

import java.util.List;

@Data
public class PublicHolidaysResponseDTO {
    private List<HolidayResponseDTO> holidays;
}
