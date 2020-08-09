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
        this.invoiceEntity = invoiceEntity;
        this.invoiceDetailResponse = invoiceDetailResponse;
    }

    public Invoice(InvoiceEntity invoiceEntity) {
        this.invoiceEntity = invoiceEntity;
    }

    public InvoiceStatus getStatus() {
        if (invoiceEntity!=null) {
            return invoiceEntity.getStatus();
        } else {
            return null;
        }
    }

    public InvoiceEntity getInvoiceEntity() {
        return invoiceEntity;
    }

    public void setInvoiceEntity(InvoiceEntity invoiceEntity) {
        this.invoiceEntity = invoiceEntity;
    }

    public InvoiceDetailResponse getInvoiceDetailResponse() {
        return invoiceDetailResponse;
    }

    public void setInvoiceDetailResponse(InvoiceDetailResponse invoiceDetailResponse) {
        this.invoiceDetailResponse = invoiceDetailResponse;
    }
}
