package com.nathaliadv.calendataapi.adapter.outbound;

import com.nathaliadv.calendataapi.adapter.outbound.http.PublicHolidayApiClient;
import com.nathaliadv.calendataapi.adapter.outbound.http.dto.PublicHolidaysResponseDTO;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class HolidayDataAdapter implements HolidayDataOutboundPort {

    private final PublicHolidayApiClient publicHolidayApiClient;

    @Override
    public List<HolidayResponse> getAllHolidaysByYearAndCountry(HolidayAnalysisRequest request) {
        PublicHolidaysResponseDTO responseDTO = publicHolidayApiClient.getPublicHolidays(request.getCountryCode(), request.getYear());
        return convertToHolidaysResponse(responseDTO);
    }

    private List<HolidayResponse> convertToHolidaysResponse(PublicHolidaysResponseDTO publicHolidaysResponseDTO) {
        return publicHolidaysResponseDTO.holidays().stream()
                .map(holidayResponseDTO -> HolidayResponse.from(holidayResponseDTO))
                .toList();
    }
}
