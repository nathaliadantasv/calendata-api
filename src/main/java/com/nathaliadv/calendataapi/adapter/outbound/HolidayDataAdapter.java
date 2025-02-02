package com.nathaliadv.calendataapi.adapter.outbound;

import com.nathaliadv.calendataapi.adapter.outbound.http.PublicHolidayApiClient;
import com.nathaliadv.calendataapi.adapter.outbound.http.dto.HolidayResponseDTO;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import com.nathaliadv.calendataapi.shared.exceptions.PublicHolidayApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class HolidayDataAdapter implements HolidayDataOutboundPort {

    private final PublicHolidayApiClient publicHolidayApiClient;

    @Override
    public List<HolidayResponse> getAllHolidaysByYearAndCountry(HolidayAnalysisRequest request) {
        try {
            List<HolidayResponseDTO> responseDTO = publicHolidayApiClient.getPublicHolidays(request.getCountryCode(), request.getYear());
            return convertToHolidaysResponse(responseDTO);
        } catch (PublicHolidayApiException e) {
            log.error("Error calling PublicHoliday API for country: {} and year: {}", request.getCountryCode(), request.getYear(), e);
            throw new PublicHolidayApiException("Error calling PublicHoliday API for country: "
                    + request.getCountryCode() + " and year: " + request.getYear(), e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while fetching holidays for country: {} and year: {}", request.getCountryCode(), request.getYear(), e);
            throw new RuntimeException("Unexpected error while fetching holidays.", e);
        }
    }

    private List<HolidayResponse> convertToHolidaysResponse(List<HolidayResponseDTO> responseDTO) {
        return responseDTO.stream()
                .map(holidayResponseDTO -> HolidayResponse.from(holidayResponseDTO))
                .toList();
    }
}
