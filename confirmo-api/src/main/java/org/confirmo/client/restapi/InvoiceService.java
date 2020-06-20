package org.confirmo.client.restapi;

import org.confirmo.client.restapi.schema.CreateNewInvoiceRequest;
import org.confirmo.client.restapi.schema.CreateNewInvoiceResponse;
import org.confirmo.client.restapi.schema.InvoiceDetailResponse;
import org.confirmo.client.restapi.schema.InvoicesResponse;

public interface InvoiceService {

    InvoicesResponse getAll(FilteringParams filteringParams);

    InvoiceDetailResponse getOne(String invoiceId);

    CreateNewInvoiceResponse post(CreateNewInvoiceRequest createNewInvoiceRequest);
}
