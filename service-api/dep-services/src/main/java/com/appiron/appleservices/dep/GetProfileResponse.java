package com.appiron.appleservices.dep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Get details about a profile.
 *
 * @author hpg
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GetProfileResponse extends Profile {

    private static final long serialVersionUID = 42L;

    /**
     * The unique identifier for a profile.
     */
    @JsonProperty("profile_uuid")
    private String profileUUID;

}
