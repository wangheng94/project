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
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DefineProfileResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * <t>A dictionary of devices. Each key in this dictionary is the serial number of a device in the
     * original request. Each value is one of the following strings:</t>
     * <p> • SUCCESS: The profile was mapped to the device.
     * <p> • NOT_ACCESSIBLE: A device with the specified serial number was not accessible by this server.
     * <p> • FAILED: Assigning the profile failed for an unexpected reason. If three retries fail, the user should contact Apple support.
     */
    @JsonProperty("devices")
    private Map<String, Object> devices;

    /**
     * The profileʼs UUID (hex string).
     */
    @JsonProperty("profile_uuid")
    private String profileUUID;
}
