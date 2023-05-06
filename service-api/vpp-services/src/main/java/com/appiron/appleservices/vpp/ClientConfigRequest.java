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
 * The request for the client configuration.
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ClientConfigRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    @JsonProperty("notificationTypes")
    private List<String> notificationTypes;

    @JsonProperty("notificationUrl")
    private String notificationUrl;

    @JsonProperty("mdmInfo")
    private MdmInfo mdmInfo;

    @JsonProperty("notificationAuthToken")
    private String notificationAuthToken;

}
