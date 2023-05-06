package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * The response that contains the service configuration.
 *
 * @author hpg
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ServiceConfigResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * The list of possible error numbers and their human-readable explanations.
     */
    @JsonProperty("errorCodes")
    private List<ResponseErrorCode> errorCodes;

    /**
     * The set of current request limits.
     * The MDM server checks these values every 5 minutes because they might change without notice.
     */
    @JsonProperty("limits")
    private Map<String, Object> limits;

    /**
     * The set of supported notification types.
     */
    @JsonProperty("notificationTypes")
    private List<String> notificationTypes;

    /**
     * The set of current service URLs.
     * The MDM server checks these values every 5 minutes because they might change without notice.
     */
    @JsonProperty("urls")
    private Map<String, Object> urls;
}
