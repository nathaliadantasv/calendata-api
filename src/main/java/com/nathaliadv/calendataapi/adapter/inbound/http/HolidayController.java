package com.nathaliadv.calendataapi.adapter.inbound.http;

import com.nathaliadv.calendataapi.adapter.inbound.http.dto.CommonHolidaysResponseDTO;
import com.nathaliadv.calendataapi.adapter.inbound.http.dto.LastHolidaysResponseDTO;
import com.nathaliadv.calendataapi.adapter.inbound.http.dto.NonWeekendHolidaysResponseDTO;
import com.nathaliadv.calendataapi.core.model.CommonHolidaysResponse;
import com.nathaliadv.calendataapi.core.model.CountryHolidayStatsResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.ports.inbound.HolidayInboundPort;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayInboundPort holidayInboundPort;

    @GetMapping("/last-three/{countryCode}")
    public LastHolidaysResponseDTO getLastThreeHolidays(@PathVariable @NotBlank @Size(min = 2, max = 2) String countryCode) {
        HolidayAnalysisRequest request = HolidayAnalysisRequest.fromCurrentDate(countryCode);
        List<HolidayResponse> holidays = holidayInboundPort.getTheLastThreeHolidaysCelebrated(request);
        return LastHolidaysResponseDTO.from(holidays);
    }

    @GetMapping("/non-weekend")
    public NonWeekendHolidaysResponseDTO getPublicHolidaysNotOnWeekends(@RequestParam @NotNull Integer year,
                                                       @RequestParam @NotNull @Size(min = 1) List<@NotBlank @Size(min = 2, max = 2) String> countryCodes) {
        List<CountryHolidayStatsResponse> holidaysNonWeekend =
                holidayInboundPort.getNumberOfNonWeekendHolidaysByCountry(countryCodes, year);
        return NonWeekendHolidaysResponseDTO.from(holidaysNonWeekend);
    }

    @GetMapping("/common")
    public CommonHolidaysResponseDTO getCommonHolidays(@RequestParam @NotBlank @Size(min = 2, max = 2) String firstCountry,
                                    @RequestParam @NotBlank @Size(min = 2, max = 2) String secondCountry, @RequestParam @NotNull Integer year) {
        CommonHolidaysResponse commonHolidays = holidayInboundPort.getCommonHolidaysBetweenCountries(firstCountry, secondCountry, year);
        return CommonHolidaysResponseDTO.from(commonHolidays);
    }
}
