package com.nathaliadv.calendataapi.adapter.outbound.http;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public abstract class BaseApiClient {

    protected final RestTemplate restTemplate;

    private static final String URL_BASE = "https://date.nager.at/api/v3";

    protected <T> T get(String suffixUrl, Class<T> responseType, Object... uriVariables) {
        return restTemplate.getForObject(URL_BASE + suffixUrl, responseType, uriVariables);
    }

    protected <T, R> R post(String suffixUrl, T request, Class<R> responseType) {
        return restTemplate.postForObject(URL_BASE + suffixUrl, request, responseType);
    }

    protected <T> void put(String suffixUrl, T request) {
        restTemplate.put(URL_BASE + suffixUrl, request);
    }
}
