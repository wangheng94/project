package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The response to a request for activation lock.
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ActivationLockResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * <ul>
     * <li>SUCCESS: The device was successfully locked.</li>
     * <li>NOT_ACCESSIBLE: The device with this serial number is not accessible by this user.</li>
     * <li>ORG_NOT_SUPPORTED: The device with this serial number is not supported because it is not present in the new program.</li>
     * <li>DEVICE_NOT_SUPPORTED: The device type is not supported.</li>
     * <li>DEVICE_ALREADY_LOCKED: The device is already locked by someone.</li>
     * <li>FAILED: Activation lock of the device failed for unexpected</li>
     * </ul>
     */
    @JsonProperty("response_status")
    private String responseStatus;

    /**
     * Serial number of the device.
     */
    @JsonProperty("serial_number")
    private String serialNumber;
}
