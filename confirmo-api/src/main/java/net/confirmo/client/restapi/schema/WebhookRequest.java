package net.confirmo.client.restapi.schema;

import java.util.Objects;
import java.util.StringJoiner;

public class WebhookRequest {
    String id;                   //": "inv3e4vx8oke",
    String  address;            // "LNKKjpmRvKy96vp6oSgqtvpMvpe6tiYWd5",
    Long confirmations;
    Long confirmingSince;
    Long createdAt;             //": 1593633399,
    CustomerAmount customerAmount;
    String cryptoUri;           // "litecoin:LNKKjpmRvKy96vp6oSgqtvpMvpe6tiYWd5?amount=0.0010133",
    Flags flags;
    MerchantAmount merchantAmount;
    SettlementAmount settlementAmount;
    String notifyEmail;
    String notifyUrl;           //": "https://lenka-public.pilsfree.net/confirmo/invoiceNotification/8b474cf6-abf7-428f-a9ec-8a5b5a6810d2",
    Paid paid;
    Product product;
    Rate rate;
    String reference;       //": "8b474cf6-abf7-428f-a9ec-8a5b5a6810d2",
    String returnUrl;//": "https://lenka-public.pilsfree.net/confirmo/invoiceReceived/8b474cf6-abf7-428f-a9ec-8a5b5a6810d2",
    InvoiceDetailResponse.Status status;//"status": "active",
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

    public CustomerAmount getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(CustomerAmount customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getCryptoUri() {
        return cryptoUri;
    }

    public void setCryptoUri(String cryptoUri) {
        this.cryptoUri = cryptoUri;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public MerchantAmount getMerchantAmount() {
        return merchantAmount;
    }

    public void setMerchantAmount(MerchantAmount merchantAmount) {
        this.merchantAmount = merchantAmount;
    }

    public SettlementAmount getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(SettlementAmount settlementAmount) {
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

    public Paid getPaid() {
        return paid;
    }

    public void setPaid(Paid paid) {
        this.paid = paid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
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

    public InvoiceDetailResponse.Status getStatus() {
        return status;
    }

    public void setStatus(InvoiceDetailResponse.Status status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebhookRequest that = (WebhookRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WebhookRequest.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("address='" + address + "'")
                .add("confirmations=" + confirmations)
                .add("confirmingSince=" + confirmingSince)
                .add("createdAt=" + createdAt)
                .add("customerAmount=" + customerAmount)
                .add("cryptoUri='" + cryptoUri + "'")
                .add("flags=" + flags)
                .add("merchantAmount=" + merchantAmount)
                .add("settlementAmount=" + settlementAmount)
                .add("notifyEmail='" + notifyEmail + "'")
                .add("notifyUrl='" + notifyUrl + "'")
                .add("paid=" + paid)
                .add("product=" + product)
                .add("rate=" + rate)
                .add("reference='" + reference + "'")
                .add("returnUrl='" + returnUrl + "'")
                .add("status=" + status)
                .add("url='" + url + "'")
                .toString();
    }
}
