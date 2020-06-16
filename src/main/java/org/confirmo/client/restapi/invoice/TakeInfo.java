
package org.confirmo.client.restapi.invoice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "taken",
    "takenAt",
    "expiration",
    "expirationDuration",
    "originalSettlementAmount",
    "offeredPrice",
    "offeredSettlementAmount",
    "customerCurrency",
    "settlementCurrency",
    "refundAmount null"
})
public class TakeInfo implements Serializable
{

    @JsonProperty("taken")
    private Boolean taken;
    @JsonProperty("takenAt")
    private Double takenAt;
    @JsonProperty("expiration")
    private Double expiration;
    @JsonProperty("expirationDuration")
    private Double expirationDuration;
    @JsonProperty("originalSettlementAmount")
    private Double originalSettlementAmount;
    @JsonProperty("offeredPrice")
    private Double offeredPrice;
    @JsonProperty("offeredSettlementAmount")
    private Double offeredSettlementAmount;
    @JsonProperty("customerCurrency")
    private String customerCurrency;
    @JsonProperty("settlementCurrency")
    private String settlementCurrency;
    @JsonProperty("refundAmount null")
    private Double refundAmountNull;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3821882740059706777L;

    @JsonProperty("taken")
    public Boolean getTaken() {
        return taken;
    }

    @JsonProperty("taken")
    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    @JsonProperty("takenAt")
    public Double getTakenAt() {
        return takenAt;
    }

    @JsonProperty("takenAt")
    public void setTakenAt(Double takenAt) {
        this.takenAt = takenAt;
    }

    @JsonProperty("expiration")
    public Double getExpiration() {
        return expiration;
    }

    @JsonProperty("expiration")
    public void setExpiration(Double expiration) {
        this.expiration = expiration;
    }

    @JsonProperty("expirationDuration")
    public Double getExpirationDuration() {
        return expirationDuration;
    }

    @JsonProperty("expirationDuration")
    public void setExpirationDuration(Double expirationDuration) {
        this.expirationDuration = expirationDuration;
    }

    @JsonProperty("originalSettlementAmount")
    public Double getOriginalSettlementAmount() {
        return originalSettlementAmount;
    }

    @JsonProperty("originalSettlementAmount")
    public void setOriginalSettlementAmount(Double originalSettlementAmount) {
        this.originalSettlementAmount = originalSettlementAmount;
    }

    @JsonProperty("offeredPrice")
    public Double getOfferedPrice() {
        return offeredPrice;
    }

    @JsonProperty("offeredPrice")
    public void setOfferedPrice(Double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    @JsonProperty("offeredSettlementAmount")
    public Double getOfferedSettlementAmount() {
        return offeredSettlementAmount;
    }

    @JsonProperty("offeredSettlementAmount")
    public void setOfferedSettlementAmount(Double offeredSettlementAmount) {
        this.offeredSettlementAmount = offeredSettlementAmount;
    }

    @JsonProperty("customerCurrency")
    public String getCustomerCurrency() {
        return customerCurrency;
    }

    @JsonProperty("customerCurrency")
    public void setCustomerCurrency(String customerCurrency) {
        this.customerCurrency = customerCurrency;
    }

    @JsonProperty("settlementCurrency")
    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    @JsonProperty("settlementCurrency")
    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    @JsonProperty("refundAmount null")
    public Double getRefundAmountNull() {
        return refundAmountNull;
    }

    @JsonProperty("refundAmount null")
    public void setRefundAmountNull(Double refundAmountNull) {
        this.refundAmountNull = refundAmountNull;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
