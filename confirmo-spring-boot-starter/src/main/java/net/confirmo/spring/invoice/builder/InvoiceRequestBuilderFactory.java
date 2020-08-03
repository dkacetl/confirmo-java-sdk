package net.confirmo.spring.invoice.builder;

import net.confirmo.api.tools.InvoiceRequestBuilder;
import net.confirmo.api.tools.InvoiceRequestCustomizer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Preconfigured invoice request builder for smarter invoice requests creations.
 *
 * invoice request can be customized by {@link net.confirmo.api.tools.InvoiceRequestCustomizer} present on spring context.
 */
public class InvoiceRequestBuilderFactory {

    /** customization every invoice request. */
    private InvoiceRequestCustomizer[] invoiceRequestCustomizers;

    @Autowired
    public InvoiceRequestBuilderFactory(@Autowired(required = false) InvoiceRequestCustomizer[] invoiceRequestCustomizers) {
        this.invoiceRequestCustomizers = invoiceRequestCustomizers;
    }

    /**
     * Create common builder for Invoice requests
     * @return InvoiceRequestBuilder
     */
    public InvoiceRequestBuilder create() {
        return new InvoiceRequestBuilder(invoiceRequestCustomizers);
    }
}
