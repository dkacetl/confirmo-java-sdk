package net.confirmo.appexample.db;

/**
 * All possible statuses
 */
public enum InvoiceStatus {
    created(false),     // invoce is created, but confirmo invoice id is not known
    prepared(false),    // invoice has been created, id is known
    active(true),
    confirming(true),
    confirmed(true),
    paid(true),
    canceled(false),
    expired(false),
    error(true);

    private boolean confirmoInvoiceDetailAccessible;

    InvoiceStatus(boolean confirmoInvoiceDetailAccessible) {
        this.confirmoInvoiceDetailAccessible = confirmoInvoiceDetailAccessible;
    }

    public boolean isConfirmoInvoiceDetailAccessible() {
        return confirmoInvoiceDetailAccessible;
    }
}
