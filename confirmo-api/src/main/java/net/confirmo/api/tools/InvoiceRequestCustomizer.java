package net.confirmo.api.tools;

import net.confirmo.api.model.CreateNewInvoiceRequest;

/**
 * Easy way to modify invoice request. In combination of {@see InvoiceRequestBuilder} every invoice request can be
 * customized the same way.
 */
public interface InvoiceRequestCustomizer {

    /**
     * Modify request
     *
     * @param createNewInvoiceRequest request for customization
     * @return customized request, can be the same instance as in parameter
     */
    CreateNewInvoiceRequest customize(CreateNewInvoiceRequest createNewInvoiceRequest);
}
