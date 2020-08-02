package net.confirmo.spring.invoice.builder;

import net.confirmo.spring.ConfirmoApiClientProperties;
import net.confirmo.api.tools.InvoiceRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyBasedInvoiceRequestBuilderCustomizer implements InvoiceRequestBuilderCustomizer {

    private ConfirmoApiClientProperties confirmoApiClientProperties;

    @Autowired
    public PropertyBasedInvoiceRequestBuilderCustomizer(ConfirmoApiClientProperties confirmoApiClientProperties) {
        this.confirmoApiClientProperties = confirmoApiClientProperties;
    }

    @Override
    public InvoiceRequestBuilder customize(InvoiceRequestBuilder invoiceRequestBuilder) {
        return invoiceRequestBuilder
                .settlement(confirmoApiClientProperties.getInvoiceSettlementCurrency())
                .invoice(confirmoApiClientProperties.getInvoiceCurrencyFrom(), 0.1f, confirmoApiClientProperties.getInvoiceCurrencyTo());

    }
}
