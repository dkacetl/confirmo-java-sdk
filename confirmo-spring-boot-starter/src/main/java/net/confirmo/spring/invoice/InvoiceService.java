package net.confirmo.spring.invoice;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceResponse;
import net.confirmo.api.model.InvoicesResponse;
import net.confirmo.api.query.FilteringParams;

public interface InvoiceService {

    InvoicesResponse getAll(FilteringParams filteringParams);

    InvoicesResponse getOne(String invoiceId);

    CreateNewInvoiceResponse create(CreateNewInvoiceRequest createNewInvoiceRequest);

}
