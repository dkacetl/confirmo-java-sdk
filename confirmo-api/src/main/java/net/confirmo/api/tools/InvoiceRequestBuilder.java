package net.confirmo.api.tools;

import net.confirmo.api.model.CreateNewInvoiceRequest;
import net.confirmo.api.model.CreateNewInvoiceRequestInvoice;
import net.confirmo.api.model.CreateNewInvoiceRequestProduct;
import net.confirmo.api.model.CreateNewInvoiceRequestSettlement;
import net.confirmo.api.model.Currency;

import java.text.DecimalFormat;
import java.text.Format;

/**
 * Builder pattern for easier creation of complex
 * {@link CreateNewInvoiceRequest} structure
 *
 * During final building of {@link CreateNewInvoiceRequest}, object can be customized
 * by chain of {@link InvoiceRequestCustomizer}.
 *
 * Builder can be used only once. It prevents side effects and corresponds to
 * builder design pattern.
 */
public class InvoiceRequestBuilder {

    private static final Format format = new DecimalFormat("#.########");

    /** instance of new created object */
    private CreateNewInvoiceRequest createNewInvoiceRequest;

    /** customizers of new created object */
    private InvoiceRequestCustomizer[] invoiceRequestCustomizers = new InvoiceRequestCustomizer[0];

    /** flag is set to true when build() method was executed. executed only once for security reason. **/
    private boolean built = false;

    public InvoiceRequestBuilder() {
        this.createNewInvoiceRequest = new CreateNewInvoiceRequest();

        CreateNewInvoiceRequestProduct product = new CreateNewInvoiceRequestProduct();
        this.createNewInvoiceRequest.setProduct(product);

        CreateNewInvoiceRequestSettlement settlementRequest = new CreateNewInvoiceRequestSettlement();
        this.createNewInvoiceRequest.setSettlement(settlementRequest);

        CreateNewInvoiceRequestInvoice invoice = new CreateNewInvoiceRequestInvoice();
        this.createNewInvoiceRequest.setInvoice(invoice);
    }

    /**
     * Inject cutomizers of created object
     * @param customizers customizers
     */
    public InvoiceRequestBuilder(InvoiceRequestCustomizer[] customizers) {
        this();
        this.invoiceRequestCustomizers = customizers;
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

    /**
     * Builder for invoice request instance. Building has a side effects, for prevent, can be used only once.
     *
     * @return invoice request
     * @throws IllegalStateException build() was called multiple times, this is not allowed.
     */
    public CreateNewInvoiceRequest build() throws IllegalStateException {
        if (built) {
            throw new IllegalStateException("Builder can be used only once. Please use another builder instance.");
        }
        if (invoiceRequestCustomizers!=null) {
            for (InvoiceRequestCustomizer requestCustomizer : invoiceRequestCustomizers) {
                this.createNewInvoiceRequest = requestCustomizer.customize(this.createNewInvoiceRequest);
            }
        }
        built = true; // done, set flag!
        return this.createNewInvoiceRequest;
    }

    public InvoiceRequestCustomizer[] getInvoiceRequestCustomizers() {
        return invoiceRequestCustomizers;
    }

    public void setInvoiceRequestCustomizers(InvoiceRequestCustomizer[] invoiceRequestCustomizers) {
        this.invoiceRequestCustomizers = invoiceRequestCustomizers;
    }
}
