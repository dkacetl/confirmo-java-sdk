package org.confirmo.appexample.form;


import java.util.UUID;

public class InvoiceForm {

    private long amount;

    private String reference = UUID.randomUUID().toString();

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}