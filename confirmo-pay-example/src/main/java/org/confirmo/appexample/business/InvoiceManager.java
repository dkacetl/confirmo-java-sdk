package org.confirmo.appexample.business;

import org.confirmo.ConfirmoPayExampleProperties;
import org.confirmo.client.restapi.InvoiceService;
import org.confirmo.client.restapi.schema.CreateNewInvoiceRequest;
import org.confirmo.client.restapi.schema.CreateNewInvoiceResponse;
import org.confirmo.client.restapi.schema.Invoice;
import org.confirmo.client.restapi.schema.Product;
import org.confirmo.client.restapi.schema.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.Format;

@Component
public class InvoiceManager {

    private Format format = new DecimalFormat("#.#");

    private InvoiceService invoiceService;

    private ConfirmoPayExampleProperties confirmoPayExampleProperties;

    @Autowired
    public InvoiceManager(InvoiceService invoiceService, ConfirmoPayExampleProperties confirmoPayExampleProperties) {
        this.invoiceService = invoiceService;
        this.confirmoPayExampleProperties = confirmoPayExampleProperties;
    }

    /**
     *
     * @param amount
     * @param reference
     * @return
     */
    public CreateNewInvoiceResponse createInvoice(float amount, String reference) {
        CreateNewInvoiceRequest invoiceRequest = new CreateNewInvoiceRequest();
        Product product = new Product();

        product.setName("Test");
        product.setDescription("Desc");

        Settlement settlement = new Settlement();
        settlement.setCurrency("CZK");

        Invoice invoice = new Invoice();
        invoice.setAmount(format.format(amount));
        invoice.setCurrencyFrom("CZK");
        invoice.setCurrencyTo("LTC");

        invoiceRequest.setProduct(product);
        invoiceRequest.setInvoice(invoice);
        invoiceRequest.setSettlement(settlement);
        invoiceRequest.setReference(reference);
        invoiceRequest.setNotifyUrl(confirmoPayExampleProperties.getNotifyUrl() + "/"+reference);
        invoiceRequest.setReturnUrl(confirmoPayExampleProperties.getReturnUrl() + "/"+reference);

        return invoiceService.post(invoiceRequest);
    }
}
