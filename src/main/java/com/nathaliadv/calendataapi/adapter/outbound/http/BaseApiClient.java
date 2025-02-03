package com.nathaliadv.calendataapi.adapter.outbound.http;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public abstract class BaseApiClient {

    protected final RestTemplate restTemplate;

    private static final String URL_BASE = "https://date.nager.at/api/v3";

    protected <T> T get(String suffixUrl, ParameterizedTypeReference<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(URL_BASE + suffixUrl, HttpMethod.GET, null, responseType, uriVariables).getBody();

    }

    protected <T, R> R post(String suffixUrl, T request, Class<R> responseType) {
        return restTemplate.postForObject(URL_BASE + suffixUrl, request, responseType);
    }

    protected <T> void put(String suffixUrl, T request) {
        restTemplate.put(URL_BASE + suffixUrl, request);
    }
}
