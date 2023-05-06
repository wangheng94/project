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
 * @deprecated {@link com.appiron.appleservices.dep.ActivationLockResponse }
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class ActivationLockStatusResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * SUCCESS: The device was successfully locked.
     * NOT_ACCESSIBLE: The device with this serial number is not accessible by this user.
     * ORG_NOT_SUPPORTED: The device with this serial number is not supported because it is not present in the new program.
     * DEVICE_NOT_SUPPORTED: The device type is not supported.
     * DEVICE_ALREADY_LOCKED: The device is already locked by someone.
     * FAILED: Activation lock of the device failed for unexpected
     */
    @JsonProperty("response_status")
    private String responseStatus;

    /**
     * Serial number of the device.
     */
    @JsonProperty("serial_number")
    private String serialNumber;
}
