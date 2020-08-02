package net.confirmo.spring.invoice.builder;

import net.confirmo.api.tools.InvoiceRequestBuilder;

/**
 * service helps you to create customized, prepared InvoiceRequestBuilder which contains common data
 */
public interface InvoiceRequestBuilderService {

    InvoiceRequestBuilder createBuilder();
}
