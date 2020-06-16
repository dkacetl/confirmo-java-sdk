package org.confirmo.client.restapi.invoicerequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.confirmo.client.restapi.invoice.Product;
import org.confirmo.client.restapi.invoice.Settlement;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceRequest {

    private Product product;
    private InvoiceAmount invoice;
    private Settlement settlement;

    private String notifyEmail;
    private String notifyUrl;
    private String returnUrl;
    private String reference;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InvoiceAmount getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceAmount invoice) {
        this.invoice = invoice;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
