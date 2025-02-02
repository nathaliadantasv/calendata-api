package com.nathaliadv.calendataapi.core.services;

import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.nathaliadv.calendataapi.helper.TestHelper.createHolidayResponses;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTests {

    @InjectMocks
    private HolidayService holidayService;

    @Mock
    HolidayDataOutboundPort holidayDataOutboundPort;

    @Test
    void whenThreeHolidaysAreReturned_ShouldReturnThemWithoutFurtherCalls() {
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
    }
}
