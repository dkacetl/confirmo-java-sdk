package net.confirmo.spring.invoice.builder;

import net.confirmo.api.tools.InvoiceRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneralInvoiceRequestBuilderService implements InvoiceRequestBuilderService {

    private InvoiceRequestBuilderCustomizer[] invoiceRequestBuilderCustomizers;

    @Autowired
    public GeneralInvoiceRequestBuilderService(InvoiceRequestBuilderCustomizer[] invoiceRequestBuilderCustomizers) {
        this.invoiceRequestBuilderCustomizers = invoiceRequestBuilderCustomizers;
    }

    @Override
    public InvoiceRequestBuilder createBuilder() {
        InvoiceRequestBuilder invoiceRequestBuilder = new InvoiceRequestBuilder();

        for (InvoiceRequestBuilderCustomizer customizer : invoiceRequestBuilderCustomizers) {
            invoiceRequestBuilder = customizer.customize(invoiceRequestBuilder);
        }

        return invoiceRequestBuilder;
    }
}
