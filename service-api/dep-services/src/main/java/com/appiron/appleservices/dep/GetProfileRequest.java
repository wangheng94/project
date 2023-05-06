package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Get details about a profile.
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GetProfileRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;


    @JsonProperty("profile_uuid")
    private String profileUUID;
}
