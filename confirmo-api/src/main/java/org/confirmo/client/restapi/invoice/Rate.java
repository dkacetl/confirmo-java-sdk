
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
    "currencyFrom",
    "currencyTo",
    "value"
})
public class Rate implements Serializable
{

    @JsonProperty("currencyFrom")
    private String currencyFrom;
    @JsonProperty("currencyTo")
    private String currencyTo;
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4445884047290971739L;

    @JsonProperty("currencyFrom")
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    @JsonProperty("currencyFrom")
    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    @JsonProperty("currencyTo")
    public String getCurrencyTo() {
        return currencyTo;
    }

    @JsonProperty("currencyTo")
    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
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
