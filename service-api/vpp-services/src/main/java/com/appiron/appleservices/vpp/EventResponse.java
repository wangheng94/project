package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author hpg
 * @date 2023/2/10 13:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EventResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * The unique identifier for the asynchronous event.
     */
    @JsonProperty("eventId")
    private String eventId;
    /**
     * The current information for the provided token.
     * The response only includes this field when MDM sets a value using the Client Config endpoint.
     */
    @JsonProperty("mdmInfo")
    private MdmInfo mdmInfo;
    /**
     * The token expiration date in an ISO-8601 format.
     * Note: The server shows all dates and times in UTC.
     */
    @JsonProperty("tokenExpirationDate")
    private String tokenExpirationDate;
    /**
     * The unique library identifier. When querying records using multiple tokens that may share libraries, use the uId field to filter duplicates and avoid double-counting records when different content managers upload duplicate tokens.
     */
    @JsonProperty("uId")
    private String uId;
}
