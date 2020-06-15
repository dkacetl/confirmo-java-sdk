package org.dkacetl.confirmo.apiclient.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class InvoiceService {

    private RestTemplate confirmoRestTemplate;

    public InvoiceList get() {
        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("limit",20);
        parametersMap.put("offset",0);
        parametersMap.put("order","createdAt,desc");
        parametersMap.put("createdAtFrom",0);
        parametersMap.put("createdAtTo",new Date().getTime());
        parametersMap.put("status","paid");

        ResponseEntity<InvoiceList> response = confirmoRestTemplate
                .getForEntity("/invoices?limit={limit}&offset={offset}&order={order}&createdAtFrom={createdAtFrom}&createdAtTo={createdAtTo}&status={status}",
                        InvoiceList.class,
                        parametersMap);
        return response.getBody();
    }

    @Autowired
    public void setConfirmoRestTemplate(RestTemplate confirmoRestTemplate) {
        this.confirmoRestTemplate = confirmoRestTemplate;
    }
}
