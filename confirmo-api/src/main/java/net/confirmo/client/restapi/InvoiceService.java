package net.confirmo.client.restapi;

import net.confirmo.client.restapi.schema.CreateNewInvoiceRequest;
import net.confirmo.client.restapi.schema.CreateNewInvoiceResponse;
import net.confirmo.client.restapi.schema.InvoiceDetailResponse;
import net.confirmo.client.restapi.schema.InvoicesResponse;

public interface InvoiceService {

    InvoicesResponse getAll(FilteringParams filteringParams);

    InvoiceDetailResponse getOne(String invoiceId);

    CreateNewInvoiceResponse post(CreateNewInvoiceRequest createNewInvoiceRequest);
}
