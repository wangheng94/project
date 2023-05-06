package com.appiron.appleservices.vpp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * @author hpg
 */
@Data
public class RequestUser {

    @JsonProperty("clientUserId")
    private String clientUserId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("managedAppleId")
    private String managedAppleId;
}
