package net.confirmo.spring.invoice;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceResponse;
import net.confirmo.api.query.FilteringParams;
import net.confirmo.api.model.InvoicesResponse;
import net.confirmo.api.spring.InvoiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RestTemplateInvoiceService implements InvoiceService {

    private InvoiceApi invoiceApi;

    public RestTemplateInvoiceService(@Autowired InvoiceApi invoiceApi) {
        this.invoiceApi  = invoiceApi;
    }

    public InvoicesResponse getAll(FilteringParams filteringParams) {
        return invoiceApi.getInvoicesList(
                BigDecimal.valueOf(filteringParams.getLimit()),
                BigDecimal.valueOf(filteringParams.getOffset()),
                filteringParams.getOrder().name(),
                BigDecimal.valueOf(filteringParams.getCreatedAtFrom()),
                BigDecimal.valueOf(filteringParams.getCreatedAtTo()),
                filteringParams.getStatus().name()
        );
    }

    public InvoicesResponse getOne(String invoiceId){
        return invoiceApi.getInvoiceById(invoiceId);
    }

    public CreateNewInvoiceResponse create(CreateNewInvoiceRequest createNewInvoiceRequest) {
        return invoiceApi.createNewInvoice(createNewInvoiceRequest);
    }
}
