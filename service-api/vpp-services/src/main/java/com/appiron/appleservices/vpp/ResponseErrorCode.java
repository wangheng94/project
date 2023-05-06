package com.appiron.appleservices.vpp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author hpg
 * @date 2023/2/13 10:18
 */
@Data
public class ResponseErrorCode {

    /**
     * The human-readable explanation of an error.
     */
    @JsonProperty("errorMessage")
    private String errorMessage;

    /**
     * The number that uniquely identifies an error.
     */
    @JsonProperty("errorNumber")
    private Integer errorNumber;

}
