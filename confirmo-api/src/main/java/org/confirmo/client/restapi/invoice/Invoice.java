
package org.confirmo.client.restapi.invoice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cryptoUri",
    "emailInvoiceId",
    "buttonInvoiceId",
    "flags",
    "merchantAmount",
    "settlementAmount",
    "notifyEmail",
    "notifyUrl",
    "paid",
    "paidSince",
    "product",
    "rate",
    "reference",
//    "refunds",
    "settlement",
    "requiredConfirmations",
    "requiredConfirmationsToRefund",
    "returnUrl",
    "status",
    "takeInfo",
    "timeoutTime",
//    "cryptoTransactions",
    "unhandledExceptions",
    "url",
    "refundLink",
    "id",
    "address",
    "confirmations",
    "confirmingSince",
    "createdAt",
    "customerAmount",
    "expiredSince",
    "errorSince",
    "activeSince",
    "preparedSince"
})
public class Invoice implements Serializable
{

    @JsonProperty("cryptoUri")
    private String cryptoUri;
    @JsonProperty("emailInvoiceId")
    private String emailInvoiceId;
    @JsonProperty("buttonInvoiceId")
    private String buttonInvoiceId;
    @JsonProperty("flags")
    private Flags flags;
    @JsonProperty("merchantAmount")
    private MerchantAmount merchantAmount;
    @JsonProperty("settlementAmount")
    private SettlementAmount settlementAmount;
    @JsonProperty("notifyEmail")
    private String notifyEmail;
    @JsonProperty("notifyUrl")
    private String notifyUrl;
    @JsonProperty("paid")
    private Paid paid;
    @JsonProperty("paidSince")
    private Double paidSince;
    @JsonProperty("product")
    private Product product;
    @JsonProperty("rate")
    private Rate rate;
    @JsonProperty("reference")
    private String reference;
//    @JsonProperty("refunds")
//    private String[] refunds;
    @JsonProperty("settlement")
    private Settlement settlement;
    @JsonProperty("requiredConfirmations")
    private Double requiredConfirmations;
    @JsonProperty("requiredConfirmationsToRefund")
    private Double requiredConfirmationsToRefund;
    @JsonProperty("returnUrl")
    private String returnUrl;
    @JsonProperty("status")
    private Invoice.Status status;
    @JsonProperty("takeInfo")
    private TakeInfo takeInfo;
    @JsonProperty("timeoutTime")
    private Double timeoutTime;
//    @JsonProperty("cryptoTransactions")
//    private String cryptoTransactions;
    @JsonProperty("unhandledExceptions")
    private Boolean unhandledExceptions;
    @JsonProperty("url")
    private String url;
    @JsonProperty("refundLink")
    private String refundLink;
    @JsonProperty("id")
    private String id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("confirmations")
    private Double confirmations;
    @JsonProperty("confirmingSince")
    private Double confirmingSince;
    @JsonProperty("createdAt")
    private Double createdAt;
    @JsonProperty("customerAmount")
    private CustomerAmount customerAmount;
    @JsonProperty("expiredSince")
    private Double expiredSince;
    @JsonProperty("errorSince")
    private Double errorSince;
    @JsonProperty("activeSince")
    private Double activeSince;
    @JsonProperty("preparedSince")
    private Double preparedSince;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2095472269565965140L;

    @JsonProperty("cryptoUri")
    public String getCryptoUri() {
        return cryptoUri;
    }

    @JsonProperty("cryptoUri")
    public void setCryptoUri(String cryptoUri) {
        this.cryptoUri = cryptoUri;
    }

    @JsonProperty("emailInvoiceId")
    public String getEmailInvoiceId() {
        return emailInvoiceId;
    }

    @JsonProperty("emailInvoiceId")
    public void setEmailInvoiceId(String emailInvoiceId) {
        this.emailInvoiceId = emailInvoiceId;
    }

    @JsonProperty("buttonInvoiceId")
    public String getButtonInvoiceId() {
        return buttonInvoiceId;
    }

    @JsonProperty("buttonInvoiceId")
    public void setButtonInvoiceId(String buttonInvoiceId) {
        this.buttonInvoiceId = buttonInvoiceId;
    }

    @JsonProperty("flags")
    public Flags getFlags() {
        return flags;
    }

    @JsonProperty("flags")
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    @JsonProperty("merchantAmount")
    public MerchantAmount getMerchantAmount() {
        return merchantAmount;
    }

    @JsonProperty("merchantAmount")
    public void setMerchantAmount(MerchantAmount merchantAmount) {
        this.merchantAmount = merchantAmount;
    }

    @JsonProperty("settlementAmount")
    public SettlementAmount getSettlementAmount() {
        return settlementAmount;
    }

    @JsonProperty("settlementAmount")
    public void setSettlementAmount(SettlementAmount settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    @JsonProperty("notifyEmail")
    public String getNotifyEmail() {
        return notifyEmail;
    }

    @JsonProperty("notifyEmail")
    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    @JsonProperty("notifyUrl")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    @JsonProperty("notifyUrl")
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @JsonProperty("paid")
    public Paid getPaid() {
        return paid;
    }

    @JsonProperty("paid")
    public void setPaid(Paid paid) {
        this.paid = paid;
    }

    @JsonProperty("paidSince")
    public Double getPaidSince() {
        return paidSince;
    }

    @JsonProperty("paidSince")
    public void setPaidSince(Double paidSince) {
        this.paidSince = paidSince;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty("rate")
    public Rate getRate() {
        return rate;
    }

    @JsonProperty("rate")
    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

//    @JsonProperty("refunds")
//    public String[] getRefunds() {
//        return refunds;
//    }
//
//    @JsonProperty("refunds")
//    public void setRefunds(String[] refunds) {
//        this.refunds = refunds;
//    }

    @JsonProperty("settlement")
    public Settlement getSettlement() {
        return settlement;
    }

    @JsonProperty("settlement")
    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    @JsonProperty("requiredConfirmations")
    public Double getRequiredConfirmations() {
        return requiredConfirmations;
    }

    @JsonProperty("requiredConfirmations")
    public void setRequiredConfirmations(Double requiredConfirmations) {
        this.requiredConfirmations = requiredConfirmations;
    }

    @JsonProperty("requiredConfirmationsToRefund")
    public Double getRequiredConfirmationsToRefund() {
        return requiredConfirmationsToRefund;
    }

    @JsonProperty("requiredConfirmationsToRefund")
    public void setRequiredConfirmationsToRefund(Double requiredConfirmationsToRefund) {
        this.requiredConfirmationsToRefund = requiredConfirmationsToRefund;
    }

    @JsonProperty("returnUrl")
    public String getReturnUrl() {
        return returnUrl;
    }

    @JsonProperty("returnUrl")
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    @JsonProperty("status")
    public Invoice.Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Invoice.Status status) {
        this.status = status;
    }

    @JsonProperty("takeInfo")
    public TakeInfo getTakeInfo() {
        return takeInfo;
    }

    @JsonProperty("takeInfo")
    public void setTakeInfo(TakeInfo takeInfo) {
        this.takeInfo = takeInfo;
    }

    @JsonProperty("timeoutTime")
    public Double getTimeoutTime() {
        return timeoutTime;
    }

    @JsonProperty("timeoutTime")
    public void setTimeoutTime(Double timeoutTime) {
        this.timeoutTime = timeoutTime;
    }

//    @JsonProperty("cryptoTransactions")
//    public String getCryptoTransactions() {
//        return cryptoTransactions;
//    }
//
//    @JsonProperty("cryptoTransactions")
//    public void setCryptoTransactions(String cryptoTransactions) {
//        this.cryptoTransactions = cryptoTransactions;
//    }

    @JsonProperty("unhandledExceptions")
    public Boolean getUnhandledExceptions() {
        return unhandledExceptions;
    }

    @JsonProperty("unhandledExceptions")
    public void setUnhandledExceptions(Boolean unhandledExceptions) {
        this.unhandledExceptions = unhandledExceptions;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("refundLink")
    public String getRefundLink() {
        return refundLink;
    }

    @JsonProperty("refundLink")
    public void setRefundLink(String refundLink) {
        this.refundLink = refundLink;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("confirmations")
    public Double getConfirmations() {
        return confirmations;
    }

    @JsonProperty("confirmations")
    public void setConfirmations(Double confirmations) {
        this.confirmations = confirmations;
    }

    @JsonProperty("confirmingSince")
    public Double getConfirmingSince() {
        return confirmingSince;
    }

    @JsonProperty("confirmingSince")
    public void setConfirmingSince(Double confirmingSince) {
        this.confirmingSince = confirmingSince;
    }

    @JsonProperty("createdAt")
    public Double getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("customerAmount")
    public CustomerAmount getCustomerAmount() {
        return customerAmount;
    }

    @JsonProperty("customerAmount")
    public void setCustomerAmount(CustomerAmount customerAmount) {
        this.customerAmount = customerAmount;
    }

    @JsonProperty("expiredSince")
    public Double getExpiredSince() {
        return expiredSince;
    }

    @JsonProperty("expiredSince")
    public void setExpiredSince(Double expiredSince) {
        this.expiredSince = expiredSince;
    }

    @JsonProperty("errorSince")
    public Double getErrorSince() {
        return errorSince;
    }

    @JsonProperty("errorSince")
    public void setErrorSince(Double errorSince) {
        this.errorSince = errorSince;
    }

    @JsonProperty("activeSince")
    public Double getActiveSince() {
        return activeSince;
    }

    @JsonProperty("activeSince")
    public void setActiveSince(Double activeSince) {
        this.activeSince = activeSince;
    }

    @JsonProperty("preparedSince")
    public Double getPreparedSince() {
        return preparedSince;
    }

    @JsonProperty("preparedSince")
    public void setPreparedSince(Double preparedSince) {
        this.preparedSince = preparedSince;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public enum Status {

        ACTIVE("active"),
        CONFIRMING("confirming"),
        PAID("paid"),
        EXPIRED("expired"),
        ERROR("error");
        private final String value;
        private final static Map<String, Invoice.Status> CONSTANTS = new HashMap<String, Invoice.Status>();

        static {
            for (Invoice.Status c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Status(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Invoice.Status fromValue(String value) {
            Invoice.Status constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
