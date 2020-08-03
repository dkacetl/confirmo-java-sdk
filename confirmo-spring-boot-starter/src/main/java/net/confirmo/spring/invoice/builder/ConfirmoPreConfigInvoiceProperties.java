package net.confirmo.spring.invoice.builder;

import net.confirmo.api.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Invoices can be pre-configured by properties.
 *
 * pre-configuration is made by {@link PreConfigInvoiceRequestCustomizer} with {@link InvoiceRequestBuilderFactory}
 */
@ConfigurationProperties(prefix = "confirmo.pre-config-invoice")
public class ConfirmoPreConfigInvoiceProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmoPreConfigInvoiceProperties.class);

    private Currency currencyFrom = Currency.CZK;

    private Currency currencyTo = null; // user can choose if BTC, LTC or whatever

    private Currency settlementCurrency = Currency.CZK;

    // ---------------------------------------------------------------------------------------


    public ConfirmoPreConfigInvoiceProperties() {
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Currency getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(Currency settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }
}
