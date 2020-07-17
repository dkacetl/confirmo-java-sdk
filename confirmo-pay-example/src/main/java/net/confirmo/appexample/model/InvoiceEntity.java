package net.confirmo.appexample.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Invoice Entity represents additional/redundant information of invoice which is not
 * stored in Confimo.net
 */
@Entity
@Table(name = "invoice",
        indexes = {@Index(name = "confirmoInvoiceId_idx",  columnList="confirmo_invoice_id", unique = true)})
public class InvoiceEntity {


    /**
     * connection between Confirmo and InvoiceEntity.
     */
    @Id
    @Column(name = "reference")
    private String reference;

    @Column(name = "confirmo_invoice_id")
    private String confirmoInvoiceId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "currency_from")
    private String currencyFrom;

    @Column(name = "amount_from")
    private Float amountFrom;

    @Column(name = "currency_to")
    private String currencyTo;

    @Column(name = "amount_to")
    private Float amountTo;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "bitcoinpay_status")
    private String bitcoinpayStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    public String getConfirmoInvoiceId() {
        return confirmoInvoiceId;
    }

    public void setConfirmoInvoiceId(String confirmoInvoiceId) {
        this.confirmoInvoiceId = confirmoInvoiceId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Float getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(Float amountFrom) {
        this.amountFrom = amountFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Float getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(Float amountTo) {
        this.amountTo = amountTo;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBitcoinpayStatus() {
        return bitcoinpayStatus;
    }

    public void setBitcoinpayStatus(String bitcoinpayStatus) {
        this.bitcoinpayStatus = bitcoinpayStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }


}
