package com.nathaliadv.calendataapi.adapter.outbound.http;

import com.nathaliadv.calendataapi.adapter.outbound.http.dto.PublicHolidaysResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PublicHolidayApiClient extends BaseApiClient {

    protected PublicHolidayApiClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public PublicHolidaysResponseDTO getPublicHolidays(String countryCode, Integer year) {
        String suffixUrl = String.format("/PublicHolidays/%s/%s", year, countryCode);
        return get(suffixUrl, PublicHolidaysResponseDTO.class, year, countryCode);
    }
}
