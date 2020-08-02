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
    @Column
    private String id;

    @Column(name = "confirmo_invoice_id")
    private String confirmoInvoiceId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "status")
    private String status;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfirmoInvoiceId() {
        return confirmoInvoiceId;
    }

    public void setConfirmoInvoiceId(String confirmoInvoiceId) {
        this.confirmoInvoiceId = confirmoInvoiceId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
