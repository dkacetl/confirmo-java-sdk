package net.confirmo.api.query;

/**
 * Used in redirect of payment.
 */
public enum BitcoinPayStatus {
    prepared,
    confirming,
    confirmed, // not clear
    paid,
    canceled,
    expired;
}
