package org.confirmo.appexample.form;


import java.util.UUID;

public class InvoiceForm {

    private float amount = 1;

    private String reference = UUID.randomUUID().toString(); // TODO: use internal id of payment

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}