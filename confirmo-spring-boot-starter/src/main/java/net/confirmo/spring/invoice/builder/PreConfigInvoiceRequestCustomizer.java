package net.confirmo.spring.invoice.builder;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.tools.InvoiceRequestCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Every invoice building by {@link InvoiceRequestBuilderFactory#create()} builder can be preconfigured by properties
 * <pre>{@code
 *
 * }</pre>
 */
@Component
public class PreConfigInvoiceRequestCustomizer implements InvoiceRequestCustomizer {

    private ConfirmoPreConfigInvoiceProperties confirmoPreConfigInvoiceProperties;

    public PreConfigInvoiceRequestCustomizer(@Autowired ConfirmoPreConfigInvoiceProperties confirmoPreConfigInvoiceProperties) {
        this.confirmoPreConfigInvoiceProperties = confirmoPreConfigInvoiceProperties;
    }

    @Override
    public CreateNewInvoiceRequest customize(CreateNewInvoiceRequest createNewInvoiceRequest) {
        if (confirmoPreConfigInvoiceProperties.getSettlementCurrency() != null) {
            createNewInvoiceRequest.getSettlement().currency(confirmoPreConfigInvoiceProperties.getSettlementCurrency().name());
        }
        if (confirmoPreConfigInvoiceProperties.getCurrencyFrom() !=null) {
            createNewInvoiceRequest.getInvoice().setCurrencyFrom(confirmoPreConfigInvoiceProperties.getCurrencyFrom().name());
        }
        if (confirmoPreConfigInvoiceProperties.getCurrencyTo() !=null) {
            createNewInvoiceRequest.getInvoice().setCurrencyTo(confirmoPreConfigInvoiceProperties.getCurrencyTo().name());
        }
        return createNewInvoiceRequest;
    }
}
