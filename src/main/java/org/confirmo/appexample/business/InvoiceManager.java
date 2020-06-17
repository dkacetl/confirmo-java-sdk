package org.confirmo.appexample.business;

import org.confirmo.ConfirmoPayExampleProperties;
import org.confirmo.client.restapi.ConfirmoApiClientProperties;
import org.confirmo.client.restapi.InvoiceService;
import org.confirmo.client.restapi.invoice.Invoice;
import org.confirmo.client.restapi.invoice.Product;
import org.confirmo.client.restapi.invoice.Settlement;
import org.confirmo.client.restapi.invoicerequest.InvoiceAmount;
import org.confirmo.client.restapi.invoicerequest.InvoiceRequest;
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
    public Invoice createInvoice(float amount, String reference) {
        InvoiceRequest invoiceRequest = new InvoiceRequest();
        Product product = new Product();

        product.setName("Test");
        product.setDescription("Desc");

        Settlement settlement = new Settlement("CZK");

        invoiceRequest.setProduct(product);
        invoiceRequest.setInvoice(new InvoiceAmount(format.format(amount),"CZK","LTC"));
        invoiceRequest.setSettlement(settlement);
        invoiceRequest.setReference(reference);
        invoiceRequest.setNotifyUrl(confirmoPayExampleProperties.getNotifyUrl() + "/"+reference);
        invoiceRequest.setReturnUrl(confirmoPayExampleProperties.getReturnUrl() + "/"+reference);

        return invoiceService.post(invoiceRequest);
    }
}
