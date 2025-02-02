package com.nathaliadv.calendataapi.configs;

import com.nathaliadv.calendataapi.core.ports.inbound.HolidayInboundPort;
import com.nathaliadv.calendataapi.core.ports.outbound.HolidayDataOutboundPort;
import com.nathaliadv.calendataapi.core.services.HolidayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

    @Bean
    public HolidayInboundPort holidayService(HolidayDataOutboundPort holidayDataOutboundPort) {
        return new HolidayService(holidayDataOutboundPort);
    }
}
