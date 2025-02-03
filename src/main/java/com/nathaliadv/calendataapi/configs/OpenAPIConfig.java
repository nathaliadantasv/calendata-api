package com.nathaliadv.calendataapi.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI calenDataApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("CalenData API")
                        .description("Returns information about public holidays given a year and country(ies).")
                        .version("v1.0.0")
                );
    }
}
