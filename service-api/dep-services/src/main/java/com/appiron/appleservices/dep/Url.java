package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author hpg
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Url extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * URI for the API.
     */
    @JsonProperty("uri")
    private String uri;

    /**
     * Possible values: GET, POST, PUT, DELETE.
     */
    @JsonProperty("http_method")
    private List<String> httpMethod;

    /**
     * Optional: Dictionary for limit parameter (see below).
     */
    @JsonProperty("limit")
    private Map<String, Object> limit;
}
