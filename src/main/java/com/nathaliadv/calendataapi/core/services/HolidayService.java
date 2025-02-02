package com.nathaliadv.calendataapi.core.services;

import com.nathaliadv.calendataapi.core.model.CommonHolidaysResponse;
import com.nathaliadv.calendataapi.core.model.CountryHolidayStatsResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.ports.inbound.HolidayInboundPort;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class HolidayService implements HolidayInboundPort {

    private final HolidayDataOutboundPort holidayDataOutboundPort;

    @Override
    public List<HolidayResponse> getTheLastThreeHolidaysCelebrated(HolidayAnalysisRequest request) {
        List<HolidayResponse> holidays = getHolidaysBeforeToday(request);

        return adjustHolidayList(request, holidays);
    }

    @Override
    public List<CountryHolidayStatsResponse> getNumberOfHolidaysByCountry(HolidayAnalysisRequest request) {
        return List.of();
    }

    @Override
    public CommonHolidaysResponse getCommonHolidaysBetweenCountries(HolidayAnalysisRequest request) {
        return null;
    }

    private List<HolidayResponse> getHolidaysBeforeToday(HolidayAnalysisRequest request) {
        return holidayDataOutboundPort.getAllHolidaysByYearAndCountry(request).stream()
                .filter(holiday -> holiday.date().isBefore(request.getCurrentDate()))
                .sorted((holiday1, holiday2) -> holiday2.date().compareTo(holiday1.date()))
                .toList();
    }

    private List<HolidayResponse> adjustHolidayList(HolidayAnalysisRequest request, List<HolidayResponse> holidays) {
        if (holidays.size() > 3) {
            holidays = holidays.stream()
                    .limit(3)
                    .toList();
        } else if (holidays.size() < 3) {
            int needed = 3 - holidays.size();

            HolidayAnalysisRequest newRequest = HolidayAnalysisRequest.builder()
                    .year(request.getYear() - 1)
                    .countryCode(request.getCountryCode())
                    .build();

            List<HolidayResponse> pastHolidays = getHolidaysBeforeToday(request)
                    .stream()
                    .limit(needed)
                    .toList();

            holidays.addAll(pastHolidays);
        }

        return holidays;
    }
}
