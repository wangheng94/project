package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GetAssetsRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    @JsonProperty("pageIndex")
    private Integer pageIndex;

    @JsonProperty("pageIndex")
    private String productType;

    @JsonProperty("pricingParam")
    private String pricingParam;

    @JsonProperty("revocable")
    private Boolean revocable;

    @JsonProperty("deviceAssignable")
    private String deviceAssignable;

    @JsonProperty("maxAvailableCount")
    private Integer maxAvailableCount;

    @JsonProperty("minAvailableCount")
    private Integer minAvailableCount;

    @JsonProperty("maxAssignedCount")
    private Integer maxAssignedCount;

    @JsonProperty("minAssignedCount")
    private Integer minAssignedCount;

    @JsonProperty("adamId")
    private String adamId;


}
