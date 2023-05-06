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
public class Oauth extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    @JsonProperty("consumer_key")
    private String consumerKey;

    @JsonProperty("consumer_secret")
    private String consumerSecret;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("access_secret")
    private String accessSecret;

    @JsonProperty("access_token_expiry")
    private String accessTokenExpiry;

}
