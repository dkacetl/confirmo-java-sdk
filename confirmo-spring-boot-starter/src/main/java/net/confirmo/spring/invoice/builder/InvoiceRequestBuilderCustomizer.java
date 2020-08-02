package net.confirmo.spring.invoice.builder;

import net.confirmo.api.tools.InvoiceRequestBuilder;

/**
 * How to intercept into process of creating invoice request by builder
 *
 */
public interface InvoiceRequestBuilderCustomizer {

    InvoiceRequestBuilder customize(InvoiceRequestBuilder invoiceRequestBuilder);
}
