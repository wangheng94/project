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
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeviceListRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * Array of strings that contains device serial numbers (may be empty).
     */
    @JsonProperty("devices")
    private List<String> devices;
}
