package net.confirmo.appexample.business;

import net.confirmo.appexample.ConfirmoPayExampleProperties;
import net.confirmo.client.restapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.Format;

@Component
public class InvoiceManager {

    private Format format = new DecimalFormat("#.###");

//    private InvoiceService invoiceService;

    private ConfirmoPayExampleProperties confirmoPayExampleProperties;

    @Autowired
    public InvoiceManager(ConfirmoPayExampleProperties confirmoPayExampleProperties) {
//        this.invoiceService = invoiceService;
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
        CreateNewInvoiceRequestProduct product = new CreateNewInvoiceRequestProduct();

        product.setName("Test");
        product.setDescription("Desc");

        CreateNewInvoiceRequestSettlement settlement = new CreateNewInvoiceRequestSettlement();
        settlement.setCurrency("CZK");

        CreateNewInvoiceRequestInvoice invoice = new CreateNewInvoiceRequestInvoice();
        invoice.setAmount(format.format(amount));
        invoice.setCurrencyFrom("CZK");
        invoice.setCurrencyTo("LTC");

        invoiceRequest.setProduct(product);
        invoiceRequest.setInvoice(invoice);
        invoiceRequest.setSettlement(settlement);
        invoiceRequest.setReference(reference);
        invoiceRequest.setNotifyUrl(confirmoPayExampleProperties.getNotifyUrl() + "/"+reference);
        invoiceRequest.setReturnUrl(confirmoPayExampleProperties.getReturnUrl() + "/"+reference);

        return null;//invoiceService.post(invoiceRequest);
    }
}
