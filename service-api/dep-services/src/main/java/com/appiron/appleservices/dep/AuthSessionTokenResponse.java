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
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AuthSessionTokenResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * The token service validates the request and replies with a JSON payload containing a single key, auth_session_token, that contains the new X-ADM-Auth-Session token.
     */
    @JsonProperty("auth_session_token")
    private String authSessionToken;
}
