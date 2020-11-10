package net.confirmo.client.restapi;

import net.confirmo.client.restapi.invoice.RestTemplateInvoiceService;
import net.confirmo.client.restapi.signature.BpSignatureManager;
import net.confirmo.client.restapi.signature.BpSignatureManagerImpl;
import net.confirmo.client.restapi.signature.BpSignatureRequestEntityValidator;
import net.confirmo.client.restapi.signature.RequestEntityValidator;
import net.confirmo.client.restapi.util.RawTokenHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(ConfirmoApiClientProperties.class)
public class ConfirmoConfiguration {

    @Autowired
    private ConfirmoApiClientProperties confirmoApiClientProperties;

    @Bean("invoiceService")
    @Autowired
    public InvoiceService restTemplateInvoiceService(RestTemplateBuilder restTemplateBuilder) {
        return new RestTemplateInvoiceService(this.confirmoRestTemplate(restTemplateBuilder));
    }

    @Bean("confirmoRestTemplate")
    @Autowired
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
        return new BpSignatureRequestEntityValidator(this.bpSignatureManager(), confirmoApiClientProperties);
    }

    @Bean
    public BpSignatureManager bpSignatureManager() {
        return new BpSignatureManagerImpl();
    }
}
