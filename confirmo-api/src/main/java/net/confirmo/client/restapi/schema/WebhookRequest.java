package net.confirmo.client.restapi.schema;

import net.confirmo.client.restapi.model.*;

public class WebhookRequest {
    String id;                   //": "inv3e4vx8oke",
    String  address;            // "LNKKjpmRvKy96vp6oSgqtvpMvpe6tiYWd5",
    Long confirmations;
    Long confirmingSince;
    Long createdAt;             //": 1593633399,
    CreateNewInvoiceRequestInvoice customerAmount;
    String cryptoUri;           // "litecoin:LNKKjpmRvKy96vp6oSgqtvpMvpe6tiYWd5?amount=0.0010133",
    CreateNewInvoiceResponseFlags flags;
    CreateNewInvoiceResponseMerchantAmount merchantAmount;
    CreateNewInvoiceResponseSettlement settlementAmount;
    String notifyEmail;
    String notifyUrl;           //": "https://lenka-public.pilsfree.net/confirmo/invoiceNotification/8b474cf6-abf7-428f-a9ec-8a5b5a6810d2",
    CreateNewInvoiceResponsePaid paid;
    CreateNewInvoiceRequestProduct product;
    CreateNewInvoiceResponseRate rate;
    String reference;       //": "8b474cf6-abf7-428f-a9ec-8a5b5a6810d2",
    String returnUrl;//": "https://lenka-public.pilsfree.net/confirmo/invoiceReceived/8b474cf6-abf7-428f-a9ec-8a5b5a6810d2",
    CreateNewInvoiceResponse.StatusEnum status;//"status": "active",
    String url;//": "https://confirmo.net/public/invoice/inv3e4vx8oke",
//    "settlement": null,
//    "emailInvoiceId": null,
//    "buttonInvoiceId": null,
//    "fee": null,
//    "overUnderPaidAmount": null,
//    "takeInfo": null,
//    "cryptoExternalUrl": null
//    "paidSince": null,
//    "expiredSince": null,
//    "errorSince": null,
//    "activeSince": 1593633399,
//    "preparedSince": null,
//    "timeoutTime": 1593634299,
//    "cryptoTransactions": [],
//    "unhandledExceptions": false,
//    "refunds": [],
//    "refundLink": null,
//    "requiredConfirmations": 2,
//    "requiredConfirmationsToRefund": null,

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Long confirmations) {
        this.confirmations = confirmations;
    }

    public Long getConfirmingSince() {
        return confirmingSince;
    }

    public void setConfirmingSince(Long confirmingSince) {
        this.confirmingSince = confirmingSince;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public CreateNewInvoiceRequestInvoice getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(CreateNewInvoiceRequestInvoice customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getCryptoUri() {
        return cryptoUri;
    }

    public void setCryptoUri(String cryptoUri) {
        this.cryptoUri = cryptoUri;
    }

    public CreateNewInvoiceResponseFlags getFlags() {
        return flags;
    }

    public void setFlags(CreateNewInvoiceResponseFlags flags) {
        this.flags = flags;
    }

    public CreateNewInvoiceResponseMerchantAmount getMerchantAmount() {
        return merchantAmount;
    }

    public void setMerchantAmount(CreateNewInvoiceResponseMerchantAmount merchantAmount) {
        this.merchantAmount = merchantAmount;
    }

    public CreateNewInvoiceResponseSettlement getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(CreateNewInvoiceResponseSettlement settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public CreateNewInvoiceResponsePaid getPaid() {
        return paid;
    }

    public void setPaid(CreateNewInvoiceResponsePaid paid) {
        this.paid = paid;
    }

    public CreateNewInvoiceRequestProduct getProduct() {
        return product;
    }

    public void setProduct(CreateNewInvoiceRequestProduct product) {
        this.product = product;
    }

    public CreateNewInvoiceResponseRate getRate() {
        return rate;
    }

    public void setRate(CreateNewInvoiceResponseRate rate) {
        this.rate = rate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public CreateNewInvoiceResponse.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(CreateNewInvoiceResponse.StatusEnum status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WebhookRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", confirmations=").append(confirmations);
        sb.append(", confirmingSince=").append(confirmingSince);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", customerAmount=").append(customerAmount);
        sb.append(", cryptoUri='").append(cryptoUri).append('\'');
        sb.append(", flags=").append(flags);
        sb.append(", merchantAmount=").append(merchantAmount);
        sb.append(", settlementAmount=").append(settlementAmount);
        sb.append(", notifyEmail='").append(notifyEmail).append('\'');
        sb.append(", notifyUrl='").append(notifyUrl).append('\'');
        sb.append(", paid=").append(paid);
        sb.append(", product=").append(product);
        sb.append(", rate=").append(rate);
        sb.append(", reference='").append(reference).append('\'');
        sb.append(", returnUrl='").append(returnUrl).append('\'');
        sb.append(", status=").append(status);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
