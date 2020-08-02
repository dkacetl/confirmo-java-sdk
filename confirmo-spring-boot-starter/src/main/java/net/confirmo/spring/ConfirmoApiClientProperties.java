package net.confirmo.spring;

import net.confirmo.api.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "confirmo.rest-api")
public class ConfirmoApiClientProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmoApiClientProperties.class);

    // access key to confirmo api
    private String apiKey;

    // default url to confirmo
    private String url = "https://confirmo.net/api/v3/";

    // protection of webhook endpoint ("unprotected open endpoint")
    // based on content hashing
    private String callbackPassword;

    private Currency invoiceCurrencyFrom = Currency.CZK;

    private Currency invoiceCurrencyTo = null; // user can choose if BTC, LTC or whatever

    private Currency invoiceSettlementCurrency = Currency.CZK;

    // ---------------------------------------------------------------------------------------

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCallbackPassword() {
        return callbackPassword;
    }

    public void setCallbackPassword(String callbackPassword) {
        this.callbackPassword = callbackPassword;
    }

    public Currency getInvoiceCurrencyFrom() {
        return invoiceCurrencyFrom;
    }

    public void setInvoiceCurrencyFrom(Currency invoiceCurrencyFrom) {
        this.invoiceCurrencyFrom = invoiceCurrencyFrom;
    }

    public Currency getInvoiceCurrencyTo() {
        return invoiceCurrencyTo;
    }

    public void setInvoiceCurrencyTo(Currency invoiceCurrencyTo) {
        this.invoiceCurrencyTo = invoiceCurrencyTo;
    }

    public Currency getInvoiceSettlementCurrency() {
        return invoiceSettlementCurrency;
    }

    public void setInvoiceSettlementCurrency(Currency invoiceSettlementCurrency) {
        this.invoiceSettlementCurrency = invoiceSettlementCurrency;
    }

    @PostConstruct
    public void checkApiKey() {
        if (StringUtils.isEmpty(apiKey)) {
            LOGGER.warn("ApiKey is empty, Confirmo REST API client will not work!!!!!!");
        }
        if (StringUtils.isEmpty(callbackPassword)) {
            LOGGER.warn("callbackPassword is empty, webhooks will not be so secure.");
        }
    }

}
