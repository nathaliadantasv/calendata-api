package com.nathaliadv.calendataapi.core.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HolidayAnalysisRequest {

    private Integer year;
    private String countryCode;
    private LocalDate currentDate;

    public static HolidayAnalysisRequest fromCurrentDate(String countryCode) {
        LocalDate date = LocalDate.now();
        return HolidayAnalysisRequest.builder()
                .year(date.getYear())
                .countryCode(countryCode)
                .currentDate(date)
                .build();
    }
}
