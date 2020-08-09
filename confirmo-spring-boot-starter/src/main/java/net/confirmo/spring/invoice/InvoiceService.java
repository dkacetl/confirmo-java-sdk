package net.confirmo.spring.invoice;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.InvoiceDetailResponse;
import net.confirmo.api.model.InvoicesResponse;
import net.confirmo.api.query.FilteringParams;

public interface InvoiceService {

    InvoicesResponse getAll(FilteringParams filteringParams);

    InvoiceDetailResponse getOne(String invoiceId);

    InvoiceDetailResponse create(CreateNewInvoiceRequest createNewInvoiceRequest);

}
