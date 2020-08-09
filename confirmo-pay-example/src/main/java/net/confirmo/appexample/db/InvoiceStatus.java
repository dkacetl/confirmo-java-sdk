package net.confirmo.appexample.db;

/**
 * All possible statuses
 */
public enum InvoiceStatus {
    created,     // invoce is created, but confirmo invoice id is not known
    prepared,    // invoice has been created, id is known
    active,
    confirming,
    confirmed,
    paid,
    canceled,
    expired,
    error;
}
