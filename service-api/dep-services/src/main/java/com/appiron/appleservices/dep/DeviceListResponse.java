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
public class DeviceListResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * A dictionary of devices. Each key in this dictionary is the serial number of a device in the original request. Each value is one of the following values:
     * <p>
     *     <ul>
     *         <li>SUCCESS: Device was successfully disowned.</li>
     *         <li>NOT_ACCESSIBLE: A device with the specified ID was not accessible by this MDM server.</li>
     *         <li>FAILED: Disowning the device failed for an unexpected reason. If three retries fail, the user should contact Apple support.</li>
     *     </ul>
     * <p>
     * If no devices were provided in the original request, this dictionary may be absent.
     *
     * Example:<p>
     * {
     *     "devices": {
     *         "Q16RDQ16P0": {
     *             "serial_number": "Q16RDQ16P0",
     *             "description": "IPHONE 14 PLUS BLUE 128GB-CHN",
     *             "model": "iPhone 14 Plus",
     *             "os": "iOS",
     *             "device_family": "iPhone",
     *             "color": "BLUE",
     *             "profile_uuid": "60671FDF0F92411E1179236168BCA4CB",
     *             "profile_assign_time": "2023-02-22T06:26:13Z",
     *             "profile_status": "assigned",
     *             "device_assigned_by": "system_user",
     *             "device_assigned_date": "2022-12-17T07:07:20Z",
     *             "response_status": "SUCCESS"
     *         },
     *         "T6QG9M9F4F": {
     *             "response_status": "NOT_ACCESSIBLE"
     *         },
     *         "CLWLW0WP2W": {
     *             "response_status": "NOT_ACCESSIBLE"
     *         },
     *         "Q390WDV7QP": {
     *             "serial_number": "Q390WDV7QP",
     *             "description": "IPHONE 14 PRO MAX DEEP PURPLE 256GB-CHN",
     *             "model": "iPhone 14 Pro Max",
     *             "os": "iOS",
     *             "device_family": "iPhone",
     *             "color": "DEEP PURPLE",
     *             "profile_uuid": "60671FDF0F92411E1179236168BCA4CB",
     *             "profile_assign_time": "2023-02-22T06:26:13Z",
     *             "profile_status": "assigned",
     *             "device_assigned_by": "system_user",
     *             "device_assigned_date": "2022-12-24T03:14:32Z",
     *             "response_status": "SUCCESS"
     *         }
     *     }
     * }
     * </p>
     */
    @JsonProperty("devices")
    private Map<String, Map<String, Object>> devices;
}
