package net.confirmo.spring;

import net.confirmo.api.spring.InvoiceApi;
import net.confirmo.api.spring.invoker.ApiClient;
import net.confirmo.api.tools.InvoiceRequestCustomizer;
import net.confirmo.spring.invoice.builder.ConfirmoPreConfigInvoiceProperties;
import net.confirmo.spring.invoice.RestTemplateInvoiceService;
import net.confirmo.spring.invoice.builder.InvoiceRequestBuilderFactory;
import net.confirmo.spring.invoice.builder.PreConfigInvoiceRequestCustomizer;
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
@EnableConfigurationProperties(
        {ConfirmoApiClientProperties.class,
        ConfirmoPreConfigInvoiceProperties.class})
@Import({InvoiceApi.class,
        RestTemplateInvoiceService.class,
        InvoiceRequestBuilderFactory.class
})
public class ConfirmoConfiguration {

    private ConfirmoApiClientProperties confirmoApiClientProperties;

    private ConfirmoPreConfigInvoiceProperties confirmoPreConfigInvoiceProperties;

    public ConfirmoConfiguration(@Autowired ConfirmoApiClientProperties confirmoApiClientProperties,
                                 @Autowired ConfirmoPreConfigInvoiceProperties confirmoPreConfigInvoiceProperties) {
        this.confirmoApiClientProperties = confirmoApiClientProperties;
        this.confirmoPreConfigInvoiceProperties = confirmoPreConfigInvoiceProperties;
    }

    @Bean
    public ApiClient springApiClient(@Autowired RestTemplateBuilder restTemplateBuilder)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ApiClient springApiClient = new ApiClient(restTemplate);

        springApiClient.setBasePath(confirmoApiClientProperties.getUrl());
        springApiClient.setBearerToken(confirmoApiClientProperties.getApiKey());
        return springApiClient;
    }

    @Bean
    @ConditionalOnProperty(prefix = "confirmo", name = "pre-config-invoice", matchIfMissing = true)
    public InvoiceRequestCustomizer propertyBasedInvoiceRequestCustomizer() {
        return new PreConfigInvoiceRequestCustomizer(confirmoPreConfigInvoiceProperties);
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