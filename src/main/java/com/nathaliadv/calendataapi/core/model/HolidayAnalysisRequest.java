package com.nathaliadv.calendataapi.core.model;

import com.nathaliadv.calendataapi.shared.exceptions.BadRequestException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static com.nathaliadv.calendataapi.shared.Constants.VALID_COUNTRY_CODES;

@Data
@Builder
public class HolidayAnalysisRequest {

    private Integer year;
    private String countryCode;
    private LocalDate currentDate;

    public static HolidayAnalysisRequest fromCurrentDate(String countryCode) {
        if(isNotValidCountryCode(countryCode)) {
            throw new BadRequestException("Invalid country code: " + countryCode);
        }

        LocalDate date = LocalDate.now();
        return HolidayAnalysisRequest.builder()
                .year(date.getYear())
                .countryCode(countryCode)
                .currentDate(date)
                .build();
    }

    public static HolidayAnalysisRequest fromYearAndCountryCode(Integer year, String countryCode) {
        if(isNotValidCountryCode(countryCode)) {
            throw new BadRequestException("Invalid country code: " + countryCode);
        }

        return HolidayAnalysisRequest.builder()
                .countryCode(countryCode)
                .year(year)
                .build();
    }

    private static boolean isNotValidCountryCode(String countryCode) {
        return !VALID_COUNTRY_CODES.contains(countryCode.toUpperCase());
    }
}
