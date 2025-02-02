package com.nathaliadv.calendataapi.core.services;

import com.nathaliadv.calendataapi.core.model.CommonHolidaysResponse;
import com.nathaliadv.calendataapi.core.model.CountryHolidayStatsResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.nathaliadv.calendataapi.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTests {

    @InjectMocks
    private HolidayService holidayService;

    @Mock
    HolidayDataOutboundPort holidayDataOutboundPort;

    @Test
    void whenThreeHolidaysAreReturnedForTheYear_ShouldReturnThemWithoutFurtherCalls() {
        HolidayAnalysisRequest request = HolidayAnalysisRequest.builder()
                .year(2025)
                .countryCode("NL")
                .currentDate(LocalDate.of(2025, 4, 21))
                .build();

        List<HolidayResponse> holidays = createHolidayResponses();

        when(holidayDataOutboundPort.getAllHolidaysByYearAndCountry(request)).thenReturn(holidays);

        List<HolidayResponse> result = holidayService.getTheLastThreeHolidaysCelebrated(request);

        assertEquals(3, result.size());
        verify(holidayDataOutboundPort, times(1)).getAllHolidaysByYearAndCountry(request);
        assertEquals("Eerste Paasdag", result.get(0).localName());
        assertEquals("Goede Vrijdag", result.get(1).localName());
        assertEquals("Nieuwjaarsdag", result.get(2).localName());
    }

    @Test
    void whenMoreThanThreeHolidaysAreReturnedForTheYear_ShouldReturnThemWithoutFurtherCalls() {
        HolidayAnalysisRequest request = HolidayAnalysisRequest.builder()
                .year(2025)
                .countryCode("NL")
                .currentDate(LocalDate.of(2025, 6, 24))
                .build();

        List<HolidayResponse> holidays = createHolidayResponses();

        when(holidayDataOutboundPort.getAllHolidaysByYearAndCountry(request)).thenReturn(holidays);

        List<HolidayResponse> result = holidayService.getTheLastThreeHolidaysCelebrated(request);

        assertEquals(3, result.size());
        verify(holidayDataOutboundPort, times(1)).getAllHolidaysByYearAndCountry(request);
        assertEquals("Tweede Pinksterdag", result.get(0).localName());
        assertEquals("Eerste Pinksterdag", result.get(1).localName());
        assertEquals("Hemelvaartsdag", result.get(2).localName());
    }

    @Test
    void whenLessThanThreeHolidaysAreReturned_ShouldCallAPIForPreviousYear() {
        LocalDate currentDate = LocalDate.of(2025, 2, 2);

        HolidayAnalysisRequest requestCurrentYear = HolidayAnalysisRequest.builder()
                .year(2025)
                .countryCode("NL")
                .currentDate(currentDate)
                .build();

        List<HolidayResponse> holidays = createHolidayResponses();
        List<HolidayResponse> previousYearHolidays = createPreviousYearHolidayResponses();

        when(holidayDataOutboundPort.getAllHolidaysByYearAndCountry(any(HolidayAnalysisRequest.class)))
                .thenAnswer(invocation -> {
                    HolidayAnalysisRequest request = invocation.getArgument(0);
                    return request.getYear() == 2025 ? holidays : previousYearHolidays;
                });

        List<HolidayResponse> result = holidayService.getTheLastThreeHolidaysCelebrated(requestCurrentYear);

        assertEquals(3, result.size());
        verify(holidayDataOutboundPort, times(2)).getAllHolidaysByYearAndCountry(any(HolidayAnalysisRequest.class));
        assertEquals("Nieuwjaarsdag", result.get(0).localName());
        assertEquals("Tweede Kerstdag", result.get(1).localName());
        assertEquals("Eerste Kerstdag", result.get(2).localName());
    }

    @Test
    void shouldReturnTheAListWithCountryCodesAndNumberOfHolidaysNotFallingOnWeekends() {
        List<String> countries = List.of("NL", "MX");
        Integer year = 2025;

        List<HolidayResponse> nlHolidays = createHolidayResponses();
        List<HolidayResponse> mxHolidays = createHolidayResponsesMX();

        when(holidayDataOutboundPort.getAllHolidaysByYearAndCountry(any(HolidayAnalysisRequest.class)))
                .thenAnswer(invocation -> {
                    HolidayAnalysisRequest request = invocation.getArgument(0);
                    return "NL".equals(request.getCountryCode())? nlHolidays : mxHolidays;
                });

        List<CountryHolidayStatsResponse> result = holidayService.getNumberOfHolidaysByCountry(countries, year);

        assertNotNull(result);
        assertEquals(2, result.size());

        CountryHolidayStatsResponse nlResponse = result.stream()
                .filter(res -> res.countryCode().equals("NL"))
                .findFirst().orElseThrow();
        assertEquals("NL", nlResponse.countryCode());
        assertEquals(8, nlResponse.numberOfHolidays());

        CountryHolidayStatsResponse mxResponse = result.stream()
                .filter(res -> res.countryCode().equals("MX"))
                .findFirst().orElseThrow();
        assertEquals("MX", mxResponse.countryCode());
        assertEquals(9, mxResponse.numberOfHolidays());

        verify(holidayDataOutboundPort, times(2)).getAllHolidaysByYearAndCountry(any(HolidayAnalysisRequest.class));
    }

    @Test
    void shouldReturnTheCommonHolidaysBetweenCountries() {
        String firstCountry = "NL";
        String secondCountry = "MX";
        Integer year = 2025;

        List<HolidayResponse> nlHolidays = createHolidayResponses();
        List<HolidayResponse> mxHolidays = createHolidayResponsesMX();

        when(holidayDataOutboundPort.getAllHolidaysByYearAndCountry(any(HolidayAnalysisRequest.class)))
                .thenAnswer(invocation -> {
                    HolidayAnalysisRequest request = invocation.getArgument(0);
                    return "NL".equals(request.getCountryCode())? nlHolidays : mxHolidays;
                });

        CommonHolidaysResponse result = holidayService.getCommonHolidaysBetweenCountries(firstCountry, secondCountry, year);

        assertNotNull(result);
        assertEquals(3, result.commonHolidays().size());
    }

    @Test
    void shouldReturnTheCommonHolidaysBetweenCountriesAsAEmptyListWhenThereAreNoCommonHolidays() {
        String firstCountry = "NL";
        String secondCountry = "FAKE";
        Integer year = 2025;

        List<HolidayResponse> nlHolidays = createHolidayResponses();
        List<HolidayResponse> fakeHolidays = createHolidayResponsesFakeCountry();

        when(holidayDataOutboundPort.getAllHolidaysByYearAndCountry(any(HolidayAnalysisRequest.class)))
                .thenAnswer(invocation -> {
                    HolidayAnalysisRequest request = invocation.getArgument(0);
                    return "NL".equals(request.getCountryCode())? nlHolidays : fakeHolidays;
                });

        CommonHolidaysResponse result = holidayService.getCommonHolidaysBetweenCountries(firstCountry, secondCountry, year);

        assertNotNull(result);
        assertTrue(result.commonHolidays().isEmpty());
    }
}
