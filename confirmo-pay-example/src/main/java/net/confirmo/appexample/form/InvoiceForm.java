package net.confirmo.appexample.form;


public class InvoiceForm {

    private float amount = 0.5f;

    private CurrencyForm currencyForm;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public CurrencyForm getCurrencyForm() {
        return currencyForm;
    }

    public void setCurrencyForm(CurrencyForm currencyForm) {
        this.currencyForm = currencyForm;
    }
}