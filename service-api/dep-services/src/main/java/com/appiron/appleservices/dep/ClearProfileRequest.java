package com.appiron.appleservices.dep;

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
public class ClearProfileRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * An array of strings containing device serial numbers.
     */
    @JsonProperty("devices")
    private String[] devices;

    /**
     * The unique identifier for a profile.
     */
    @JsonProperty("profile_uuid")
    private String profileUUID;
}
