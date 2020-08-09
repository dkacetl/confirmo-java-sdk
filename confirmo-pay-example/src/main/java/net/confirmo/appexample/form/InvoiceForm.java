package net.confirmo.appexample.form;


public class InvoiceForm {

    private float amount = 0.5f;

    private TargetCryptocurrencySelection targetCryptocurrencySelection;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public TargetCryptocurrencySelection getTargetCryptocurrencySelection() {
        return targetCryptocurrencySelection;
    }

    public void setTargetCryptocurrencySelection(TargetCryptocurrencySelection targetCryptocurrencySelection) {
        this.targetCryptocurrencySelection = targetCryptocurrencySelection;
    }
}