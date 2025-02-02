package com.nathaliadv.calendataapi.core.services;

import com.nathaliadv.calendataapi.core.model.*;
import com.nathaliadv.calendataapi.core.ports.inbound.HolidayInboundPort;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HolidayService implements HolidayInboundPort {

    private final HolidayDataOutboundPort holidayDataOutboundPort;

    @Override
    public List<HolidayResponse> getTheLastThreeHolidaysCelebrated(HolidayAnalysisRequest request) {
        List<HolidayResponse> holidays = getHolidaysBeforeToday(request, 3);
        return adjustHolidayList(request, holidays);
    }

    @Override
    public List<CountryHolidayStatsResponse> getNumberOfNonWeekendHolidaysByCountry(List<String> countries, Integer year) {
        List<CountryHolidayStatsResponse> result = new ArrayList<>();
        for (String country : countries) {
            List<HolidayResponse> holidaysInTheYear = getHolidaysFromYear(country, year);
            result.add(CountryHolidayStatsResponse.from(holidaysInTheYear, country));
        }
        return result;
    }

    @Override
    public CommonHolidaysResponse getCommonHolidaysBetweenCountries(String firstCountry, String secondCountry, Integer year) {
        List<HolidayResponse> holidaysFirstCountry = getHolidaysFromYear(firstCountry, year);
        List<HolidayResponse> holidaysSecondCountry = getHolidaysFromYear(secondCountry, year);

        List<CommonHoliday> commonHolidays = new ArrayList<>();

        for (HolidayResponse holidayFirstCountry : holidaysFirstCountry) {
            for (HolidayResponse holidaySecondCountry : holidaysSecondCountry) {
                if (holidayFirstCountry.date().equals(holidaySecondCountry.date())) {
                    CommonHoliday commonHoliday = new CommonHoliday(
                            holidayFirstCountry.date(),
                            holidayFirstCountry.localName(),
                            holidaySecondCountry.localName()
                    );
                    commonHolidays.add(commonHoliday);
                }
            }
        }

        return CommonHolidaysResponse.builder()
                .firstCountry(firstCountry)
                .secondCountry(secondCountry)
                .commonHolidays(commonHolidays)
                .build();
    }

    private List<HolidayResponse> getHolidaysFromYear(String country, Integer year) {
        HolidayAnalysisRequest request = HolidayAnalysisRequest.builder()
                .countryCode(country)
                .year(year)
                .build();
        return holidayDataOutboundPort.getAllHolidaysByYearAndCountry(request);
    }

    private List<HolidayResponse> getHolidaysBeforeToday(HolidayAnalysisRequest request, long limit) {
        return holidayDataOutboundPort.getAllHolidaysByYearAndCountry(request).stream()
                .filter(holiday -> holiday.date().isBefore(request.getCurrentDate()))
                .sorted((holiday1, holiday2) -> holiday2.date().compareTo(holiday1.date()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private List<HolidayResponse> adjustHolidayList(HolidayAnalysisRequest request, List<HolidayResponse> holidays) {
        if (holidays.size() < 3) {
            int needed = 3 - holidays.size();
            HolidayAnalysisRequest newRequest = HolidayAnalysisRequest.builder()
                    .year(request.getYear() - 1)
                    .countryCode(request.getCountryCode())
                    .currentDate(request.getCurrentDate())
                    .build();
            List<HolidayResponse> pastHolidays = getHolidaysBeforeToday(newRequest, needed);
            holidays.addAll(pastHolidays);
        }
        return holidays;
    }
}
