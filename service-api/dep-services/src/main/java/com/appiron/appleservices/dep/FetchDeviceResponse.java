package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * The response that contains a list of devices.
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class FetchDeviceResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * A hex string that represents the starting position for a request.
     * Use this to retrieve the list of devices that have been added or removed since a previous request.
     * The string can be up to 1000 characters. On the initial request, this should be omitted.
     */
    @JsonProperty("cursor")
    private String cursor;

    /**
     * An array of dictionaries providing information about devices, sorted in chronological order
     * of enrollment from oldest to most recent.
     */
    @JsonProperty("devices")
    private List<Device> devices;

    /**
     * A timestamp indicating the progress of the device fetch request, in ISO 8601 format.
     */
    @JsonProperty("fetched_until")
    private String fetchedUntil;

    /**
     * A Boolean value that indicates whether the requestʼs limit and cursor values resulted in only
     * a partial list of devices. If true, the MDM server should then make another request
     * (starting from the newly returned cursor) to obtain additional records.
     */
    @JsonProperty("more_to_follow")
    private Boolean moreToFollow;

}
