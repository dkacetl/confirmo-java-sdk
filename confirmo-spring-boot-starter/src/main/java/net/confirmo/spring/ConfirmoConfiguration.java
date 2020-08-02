package net.confirmo.spring;

import net.confirmo.spring.invoice.builder.GeneralInvoiceRequestBuilderService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderService;
import net.confirmo.spring.invoice.builder.PropertyBasedInvoiceRequestBuilderCustomizer;
import net.confirmo.api.spring.InvoiceApi;
import net.confirmo.api.spring.invoker.ApiClient;
import net.confirmo.spring.invoice.InvoiceService;
import net.confirmo.spring.invoice.RestTemplateInvoiceService;
import net.confirmo.spring.signature.BpSignatureManager;
import net.confirmo.spring.signature.BpSignatureManagerImpl;
import net.confirmo.spring.signature.BpSignatureRequestEntityValidator;
import net.confirmo.spring.signature.RequestEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration
 */
@Configuration
@EnableConfigurationProperties(ConfirmoApiClientProperties.class)
@Import({InvoiceApi.class,
        RestTemplateInvoiceService.class,
        PropertyBasedInvoiceRequestBuilderCustomizer.class,
        GeneralInvoiceRequestBuilderService.class
})
public class ConfirmoConfiguration {

    @Autowired
    private ConfirmoApiClientProperties confirmoApiClientProperties;

    @Bean
    public ApiClient springApiClient(@Autowired RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ApiClient springApiClient = new ApiClient(restTemplate);

        springApiClient.setBasePath(confirmoApiClientProperties.getUrl());
        springApiClient.setBearerToken(confirmoApiClientProperties.getApiKey());
        return springApiClient;
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
