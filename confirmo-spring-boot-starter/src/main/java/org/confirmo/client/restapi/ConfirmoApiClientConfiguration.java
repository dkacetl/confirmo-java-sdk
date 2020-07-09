package org.confirmo.client.restapi;

import org.confirmo.client.restapi.signature.BpSignatureManager;
import org.confirmo.client.restapi.signature.BpSignatureRequestEntityValidator;
import org.confirmo.client.restapi.signature.RequestEntityValidator;
import org.confirmo.client.restapi.util.RawTokenHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    @Autowired
    private BpSignatureManager bpSignatureManager;

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

    /**
     * @return validator when callback-password is present only
     */
    @Bean
    @ConditionalOnProperty(prefix = "confirmo.rest-api", value = "callback-password")
    public RequestEntityValidator bpSignatureRequestEntityValidator() {
        return new BpSignatureRequestEntityValidator(bpSignatureManager, confirmoApiClientProperties);
    }
}
