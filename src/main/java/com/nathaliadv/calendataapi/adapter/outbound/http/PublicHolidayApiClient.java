package com.nathaliadv.calendataapi.adapter.outbound.http;

import com.nathaliadv.calendataapi.adapter.outbound.http.dto.HolidayResponseDTO;
import com.nathaliadv.calendataapi.shared.exceptions.PublicHolidayApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class PublicHolidayApiClient extends BaseApiClient {

    public PublicHolidayApiClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public List<HolidayResponseDTO> getPublicHolidays(String countryCode, Integer year) {
        String suffixUrl = String.format("/PublicHolidays/%s/%s", year, countryCode);
        try{
            return get(suffixUrl, new ParameterizedTypeReference<List<HolidayResponseDTO>>() {}, year, countryCode);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error calling PublicHoliday API for country: {} and year: {}", countryCode, year, e);
            throw new PublicHolidayApiException("Error calling PublicHoliday API for country: "
                    + countryCode + " and year: " + year, e);
        } catch (RestClientException e) {
            log.error("Error calling PublicHoliday API (RestClientException) for country: {} and year: {}", countryCode, year, e);
            throw new PublicHolidayApiException("Error calling PublicHoliday API for country: "
                    + countryCode + " and year: " + year, e);
        } catch (Exception e) {
            log.error("Unexpected error calling PublicHoliday API for country: {} and year: {}", countryCode, year, e);
            throw new PublicHolidayApiException("Unexpected error calling PublicHoliday API for country: "
                    + countryCode + " and year: " + year, e);
        }
    }
}
