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
 * 
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SyncDeviceRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * A hex string that represents the starting position for a request.
     * Use this to retrieve the list of devices that have been added or removed since a previous request.
     * The string can be up to 1000 characters. On the initial request, this should be omitted.
     */

    @JsonProperty("cursor")
    private String cursor;

    /**
     * The maximum number of entries to return. Optional.
     * Default: 100
     * Maximum Value: 1000
     */
    @JsonProperty("limit")
    private Integer limit;
}
