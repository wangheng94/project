package com.appiron.appleservices.vpp;

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
 * 
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ManageAssetsRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    @JsonProperty("assets")
    private List<Asset> assets;

    @JsonProperty("clientUserIds")
    private List<String> clientUserIds;

    @JsonProperty("serialNumbers")
    private List<String> serialNumbers;

}
