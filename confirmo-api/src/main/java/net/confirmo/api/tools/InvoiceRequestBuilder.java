package net.confirmo.api.tools;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceRequestInvoice;
import net.confirmo.api.model.CreateNewInvoiceRequestProduct;
import net.confirmo.api.model.CreateNewInvoiceRequestSettlement;
import net.confirmo.api.model.Currency;

import java.text.DecimalFormat;
import java.text.Format;

public class InvoiceRequestBuilder {

    private static final Format format = new DecimalFormat("#.########");

    private CreateNewInvoiceRequest createNewInvoiceRequest;

    public InvoiceRequestBuilder() {
        this.createNewInvoiceRequest = new CreateNewInvoiceRequest();

        CreateNewInvoiceRequestProduct product = new CreateNewInvoiceRequestProduct();
        this.createNewInvoiceRequest.setProduct(product);

        CreateNewInvoiceRequestSettlement settlementRequest = new CreateNewInvoiceRequestSettlement();
        this.createNewInvoiceRequest.setSettlement(settlementRequest);

        CreateNewInvoiceRequestInvoice invoice = new CreateNewInvoiceRequestInvoice();
        this.createNewInvoiceRequest.setInvoice(invoice);
    }

    public InvoiceRequestBuilder product(String name, String description) {
        CreateNewInvoiceRequestProduct product = this.createNewInvoiceRequest.getProduct();
        assert product != null;

        product.setName(name);
        product.setDescription(description);

        return this;
    }

    public InvoiceRequestBuilder settlement(Currency currency) {
        CreateNewInvoiceRequestSettlement settlementRequest = this.createNewInvoiceRequest.getSettlement();
        if (currency!=null) {
            settlementRequest.setCurrency(currency.name());
        }

        return this;
    }

    public InvoiceRequestBuilder invoice(Currency from, float amount, Currency to) {
        CreateNewInvoiceRequestInvoice invoice = this.createNewInvoiceRequest.getInvoice();
        if (from!=null) {
            invoice.setCurrencyFrom(from.name());
        }
        invoice.setAmount(format.format(amount));

        if (to!=null) {
            invoice.setCurrencyTo(to.name());
        }
        return this;
    }

    public InvoiceRequestBuilder invoiceAmount(float amount) {
        CreateNewInvoiceRequestInvoice invoice = this.createNewInvoiceRequest.getInvoice();
        invoice.setAmount(format.format(amount));
        return this;
    }

    public InvoiceRequestBuilder reference(String reference, String notifyUrl, String returnUrl) {
        this.createNewInvoiceRequest.setReference(reference);
        this.createNewInvoiceRequest.setNotifyUrl(notifyUrl);
        this.createNewInvoiceRequest.setReturnUrl(returnUrl);
        return this;
    }

    public CreateNewInvoiceRequest build() {
        return this.createNewInvoiceRequest;
    }
}
