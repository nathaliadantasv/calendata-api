package com.nathaliadv.calendataapi.core.ports.inbound;

import com.nathaliadv.calendataapi.core.model.CommonHolidaysResponse;
import com.nathaliadv.calendataapi.core.model.CountryHolidayStatsResponse;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;

import java.util.List;

public interface HolidayInboundPort {

    List<HolidayResponse> getTheLastThreeHolidaysCelebrated(HolidayAnalysisRequest request);

    List<CountryHolidayStatsResponse> getNumberOfHolidaysByCountry(HolidayAnalysisRequest request);

    CommonHolidaysResponse getCommonHolidaysBetweenCountries(HolidayAnalysisRequest request);
}
