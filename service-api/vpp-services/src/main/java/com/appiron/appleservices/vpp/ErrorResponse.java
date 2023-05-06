package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * The response that contains the error that occurs.
 *
 * @author hpg
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * The request-specific information regarding the failure.
     */
    @JsonProperty("errorInfo")
    protected ResponseErrorInfo errorInfo;

    /**
     * The human-readable error message that describes the failure.
     */
    @JsonProperty("errorMessage")
    protected String errorMessage;

    /**
     * The error number that represents the failure.
     */
    @JsonProperty("errorNumber")
    protected Integer errorNumber;

    public ErrorResponse(){

    }

    public ErrorResponse(ResponseErrorInfo errorInfo, String errorMessage, int errorNumber) {
        this.errorInfo = errorInfo;
        this.errorMessage = errorMessage;
        this.errorNumber = errorNumber;
    }
}
