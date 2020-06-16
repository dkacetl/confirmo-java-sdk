package org.confirmo.client.restapi;

import org.confirmo.client.restapi.util.RawTokenHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration
 */
@Configuration
@Import(ConfirmoApiClientProperties.class)
public class ConfirmoApiClientConfiguration {

    @Autowired
    private ConfirmoApiClientProperties confirmoApiClientProperties;

    @Bean("confirmoRestTemplate")
    public RestTemplate confirmoRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(confirmoApiClientProperties.getUrl()).build();
    }

    @Bean
    public RestTemplateCustomizer confirmoApiKeyTokenCustomizer() {
        return restTemplate -> {
            restTemplate.getInterceptors().add(new RawTokenHttpRequestInterceptor(
                    confirmoApiClientProperties.getApiKey()));
        };
    }
}
