package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hpg
 * @date 2023/2/13 9:40
 */
@Deprecated
public abstract class VPPGenerationConvert extends GenerationConvert {
    /**
     * The request-specific information regarding the failure.
     */
    @Getter
    @Setter
    protected ResponseErrorInfo errorInfo;
    /**
     * The human-readable error message that describes the failure.
     */
    @Getter
    @Setter
    protected String errorMessage;
    /**
     * The error number that represents the failure.
     */
    @Getter
    @Setter
    protected Integer errorNumber;


    public ErrorResponse errorResponse() {
        return new ErrorResponse(this.errorInfo, this.errorMessage, this.errorNumber);
    }

}
