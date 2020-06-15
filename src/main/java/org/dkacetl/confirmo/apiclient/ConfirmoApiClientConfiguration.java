package org.dkacetl.confirmo.apiclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfirmoApiClientConfiguration {

    @Value("${confirmo.api-key}")
    private String apiKey;

    @Bean("confirmoRestTemplate")
    public RestTemplate confirmoRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri("https://confirmo.net/api/v3").build();
    }

    @Bean
    public RestTemplateCustomizer rawTokenHttpRequestCustomizer() {
        return restTemplate -> {
            restTemplate.getInterceptors().add(new RawTokenHttpRequestInterceptor(apiKey));
        };
    }
}
