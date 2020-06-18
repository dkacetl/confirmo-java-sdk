package org.confirmo.client.restapi.invoice;

public class InvoiceFlags {

    private Boolean refundable;
    private String notRefundableCause; // "REFUND_AMOUNT_ZERO",
    private String resolvableStatus;//": "NO"

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public String getNotRefundableCause() {
        return notRefundableCause;
    }

    public void setNotRefundableCause(String notRefundableCause) {
        this.notRefundableCause = notRefundableCause;
    }

    public String getResolvableStatus() {
        return resolvableStatus;
    }

    public void setResolvableStatus(String resolvableStatus) {
        this.resolvableStatus = resolvableStatus;
    }
}
