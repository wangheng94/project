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
 * The request to get the status of a set of devices.
 *
 * @author hpg
 *
 * <note>
 * HTTP/1.1 200 OK
 * Date: Thu, 9 May 2013 03:24:28 GMT
 * Content-Type: application/json;charset=UTF8
 * X-ADM-Auth-Session: 87a235815b8d6661ac73329f75815b8d6661ac73329f815
 * Content-Length: 160
 * Connection: Keep-Alive
 * {
 * ”devices”: {
 * ”C8TJ500QF1MN”:”SUCCESS”,
 * ”B7CJ500QF1MA”:”NOT_ACCESSIBLE”
 * }
 * </note>
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeviceStatusResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * <p>
     * A dictionary of devices. Each key in this dictionary is the serial number of a device in the original
     * request. Each value is one of the following values:
     * • SUCCESS: Device was successfully disowned.
     * • NOT_ACCESSIBLE: A device with the specified ID was not accessible by this MDM server.
     * • FAILED: Disowning the device failed for an unexpected reason. If three retries fail, the user
     * should contact Apple support.
     * If no devices were provided in the original request, this dictionary may be absent.
     * </p>
     *
     * Example:
     * <p>
     *     {"devices":{"C6KDV0MJ0GQW":"SUCCESS"}}
     * </p>
     */
    @JsonProperty("devices")
    private Map<String, Object> devices;
}
