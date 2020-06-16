
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
    "refundable",
    "notRefundableCause",
    "resolvableStatus"
})
public class Flags implements Serializable
{

    @JsonProperty("refundable")
    private Boolean refundable;
    @JsonProperty("notRefundableCause")
    private Flags.NotRefundableCause notRefundableCause;
    @JsonProperty("resolvableStatus")
    private Flags.ResolvableStatus resolvableStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2698497326128819123L;

    @JsonProperty("refundable")
    public Boolean getRefundable() {
        return refundable;
    }

    @JsonProperty("refundable")
    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    @JsonProperty("notRefundableCause")
    public Flags.NotRefundableCause getNotRefundableCause() {
        return notRefundableCause;
    }

    @JsonProperty("notRefundableCause")
    public void setNotRefundableCause(Flags.NotRefundableCause notRefundableCause) {
        this.notRefundableCause = notRefundableCause;
    }

    @JsonProperty("resolvableStatus")
    public Flags.ResolvableStatus getResolvableStatus() {
        return resolvableStatus;
    }

    @JsonProperty("resolvableStatus")
    public void setResolvableStatus(Flags.ResolvableStatus resolvableStatus) {
        this.resolvableStatus = resolvableStatus;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public enum NotRefundableCause {

        REFUND_AMOUNT_ZERO("REFUND_AMOUNT_ZERO"),
        REFUND_AMOUNT_TOO_LOW("REFUND_AMOUNT_TOO_LOW"),
        WRONG_INVOICE_STATE("WRONG_INVOICE_STATE"),
        NOT_ALLOWED("NOT_ALLOWED");
        private final String value;
        private final static Map<String, Flags.NotRefundableCause> CONSTANTS = new HashMap<String, Flags.NotRefundableCause>();

        static {
            for (Flags.NotRefundableCause c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private NotRefundableCause(String value) {
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
        public static Flags.NotRefundableCause fromValue(String value) {
            Flags.NotRefundableCause constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum ResolvableStatus {

        YES("YES"),
        WAITING("WAITING"),
        NO("NO");
        private final String value;
        private final static Map<String, Flags.ResolvableStatus> CONSTANTS = new HashMap<String, Flags.ResolvableStatus>();

        static {
            for (Flags.ResolvableStatus c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ResolvableStatus(String value) {
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
        public static Flags.ResolvableStatus fromValue(String value) {
            Flags.ResolvableStatus constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
