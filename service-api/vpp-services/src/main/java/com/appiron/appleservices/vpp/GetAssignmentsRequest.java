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
public class GetAssignmentsRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    @JsonProperty("adamId")
    private String adamId;

    @JsonProperty("clientUserId")
    private String clientUserId;

    @JsonProperty("pageIndex")
    private Integer pageIndex;

    @JsonProperty("serialNumber")
    private String serialNumber;

    @JsonProperty("sinceVersionId")
    private String sinceVersionId;
}
