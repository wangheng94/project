package com.appiron.appleservices.vpp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * A product in the store.
 * </p>
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
public class Asset {

    /**
     * The unique identifier for a product in the store.
     */
    @JsonProperty("adamId")
    protected String adamId;

    /**
     * The quality of a product in the store.
     */
    @JsonProperty("pricingParam")
    protected String pricingParam;
}
