package net.confirmo.client.restapi.invoice;

import net.confirmo.client.restapi.FilteringParams;
import net.confirmo.client.restapi.InvoiceService;
import net.confirmo.client.restapi.schema.CreateNewInvoiceRequest;
import net.confirmo.client.restapi.schema.CreateNewInvoiceResponse;
import net.confirmo.client.restapi.schema.InvoiceDetailResponse;
import net.confirmo.client.restapi.schema.InvoicesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestTemplateInvoiceService implements InvoiceService {

    private RestTemplate confirmoRestTemplate;

    @Autowired
    public RestTemplateInvoiceService(RestTemplate confirmoRestTemplate) {
        this.confirmoRestTemplate = confirmoRestTemplate;
    }

    public InvoicesResponse getAll(FilteringParams filteringParams) {
        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("limit", filteringParams.getLimit());
        parametersMap.put("offset", filteringParams.getOffset());
        parametersMap.put("order",  filteringParams.getOrder() + "," + (filteringParams.getAsc()?"asc":"desc"));
        parametersMap.put("createdAtFrom", filteringParams.getCreatedAtFrom());
        parametersMap.put("createdAtTo", filteringParams.getCreatedAtTo());
        parametersMap.put("status", filteringParams.getStatus());

        ResponseEntity<InvoicesResponse> response = confirmoRestTemplate
                .getForEntity("/invoices?limit={limit}&offset={offset}&order={order}&createdAtFrom={createdAtFrom}&createdAtTo={createdAtTo}&status={status}",
                        InvoicesResponse.class,
                        parametersMap);
        return response.getBody();
    }

    public InvoiceDetailResponse getOne(String invoiceId){
        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("invoiceId",invoiceId);

        ResponseEntity<InvoiceDetailResponse> response = confirmoRestTemplate
                .getForEntity("/invoices/{invoiceId}",
                        InvoiceDetailResponse.class,
                        parametersMap);
        return response.getBody();
    }

    public CreateNewInvoiceResponse post(CreateNewInvoiceRequest createNewInvoiceRequest) {
        ResponseEntity<CreateNewInvoiceResponse> response = confirmoRestTemplate.postForEntity("/invoices", createNewInvoiceRequest, CreateNewInvoiceResponse.class);
        return response.getBody();
    }
}
