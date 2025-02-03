package com.nathaliadv.calendataapi.adapter.outbound;

import com.nathaliadv.calendataapi.adapter.outbound.http.PublicHolidayApiClient;
import com.nathaliadv.calendataapi.adapter.outbound.http.dto.HolidayResponseDTO;
import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import com.nathaliadv.calendataapi.shared.exceptions.BadGatewayException;
import com.nathaliadv.calendataapi.shared.exceptions.BadRequestException;
import com.nathaliadv.calendataapi.shared.exceptions.ServiceUnavailableException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

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
        } catch (HttpClientErrorException e) {
            log.error("Client error occurred while fetching holidays for country: {} and year: {}", request.getCountryCode(), request.getYear(), e);
            throw new BadRequestException("Client error occurred while fetching holidays.", e);
        } catch (HttpServerErrorException e) {
            log.error("Server error occurred while fetching holidays for country: {} and year: {}", request.getCountryCode(), request.getYear(), e);
            throw new BadGatewayException("Server error occurred while fetching holidays.", e);
        } catch (RestClientException e) {
            log.error("Error occurred while calling PublicHoliday API for country: {} and year: {}", request.getCountryCode(), request.getYear(), e);
            throw new ServiceUnavailableException("Service unavailable while fetching holidays.", e);
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
