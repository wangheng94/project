package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * The response to assigning a profile to a set of devices.
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AssignProfileResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * A dictionary of devices. Each key in this dictionary is the serial number of a device in the original request. Each value is one of the following strings:
     * <p>
     *     <ul>
     *         <li>SUCCESS: The profile was mapped to the device.</li>
     *         <li>NOT_ACCESSIBLE: A device with the specified serial number was not accessible by this server.</li>
     *         <li>FAILED: Assigning the profile failed for an unexpected reason. If three retries fail, the user should contact Apple support.</li>
     *     </ul>
     * <p>
     * The unique identifier for a profile.
     */
    @JsonProperty("devices")
    private Map<String, Object> devices;

    /**
     * The unique identifier for a profile.
     */
    @JsonProperty("profile_uuid")
    private String profileUUID;
}
