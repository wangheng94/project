package com.appiron.appleservices.vpp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author hpg
 */
@Data
public class Assignment {

    /**
     * The unique identifier for a product in the store.
     */
    @JsonProperty("adamId")
    private String adamId;
    /**
     * The unique identifier for an active user in your organization.
     */
    @JsonProperty("clientUserId")
    private String clientUserId;
    /**
     * The quality of a product in the store.
     * </br>
     * Possible values: STDQ, PLUS
     */
    @JsonProperty("pricingParam")
    private String pricingParam;
    /**
     * The unique identifier for a device in your organization.
     */
    @JsonProperty("serialNumber")
    private String serialNumber;
}
