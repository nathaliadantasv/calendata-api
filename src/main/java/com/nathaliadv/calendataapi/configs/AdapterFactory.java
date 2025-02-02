package com.nathaliadv.calendataapi.configs;

import com.nathaliadv.calendataapi.adapter.outbound.HolidayDataAdapter;
import com.nathaliadv.calendataapi.adapter.outbound.http.PublicHolidayApiClient;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AdapterFactory {

    @Bean
    HolidayDataOutboundPort holidayDataAdapter(PublicHolidayApiClient publicHolidayApiClient) {
        return new HolidayDataAdapter(publicHolidayApiClient);
    }

    @Bean
    PublicHolidayApiClient publicHolidayApiClient(RestTemplate restTemplate) {
        return new PublicHolidayApiClient(restTemplate);
    }
}
