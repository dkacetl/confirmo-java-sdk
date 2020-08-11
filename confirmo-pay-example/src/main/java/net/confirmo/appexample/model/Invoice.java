package net.confirmo.appexample.model;

import net.confirmo.api.model.InvoiceDetailResponse;
import net.confirmo.appexample.db.InvoiceEntity;
import net.confirmo.appexample.db.InvoiceStatus;


/**
 * Wrapping structure of invoice with all detail
 */
public class Invoice {

    private InvoiceEntity invoiceEntity;
    private InvoiceDetailResponse invoiceDetailResponse;

    public Invoice(InvoiceEntity invoiceEntity, InvoiceDetailResponse invoiceDetailResponse) {
        if (invoiceEntity==null) {
            throw new IllegalArgumentException("InvoiceEntity cannot be null");
        }
        this.invoiceEntity = invoiceEntity;
        this.invoiceDetailResponse = invoiceDetailResponse;
    }

    public Invoice(InvoiceEntity invoiceEntity) {
        this(invoiceEntity,null);
    }

    public InvoiceStatus getStatus() {
        if (invoiceDetailResponse!=null) {
            if (invoiceDetailResponse.getStatus()!=null)
                return InvoiceStatus.valueOf(invoiceDetailResponse.getStatus().getValue().toString());
        } else {
            if (invoiceEntity.getStatus()!=null) {
                return invoiceEntity.getStatus();
            }
        }
        throw new IllegalStateException("Invoice not loaded or unknown state.");
    }

    public InvoiceEntity getInvoiceEntity() {
        return invoiceEntity;
    }

    public InvoiceDetailResponse getInvoiceDetailResponse() {
        return invoiceDetailResponse;
    }
}
