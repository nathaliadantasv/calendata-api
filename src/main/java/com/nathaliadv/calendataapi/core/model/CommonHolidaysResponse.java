package com.nathaliadv.calendataapi.core.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommonHolidaysResponse {

    private String firstCountry;
    private String secondCountry;
    private List<CommonHoliday> commonHolidays;
}
