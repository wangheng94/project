package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author hpg
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class StatusResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;
    /**
     * The current status of the asynchronous event.
     * Possible values: PENDING, COMPLETE, FAILED
     */
    private String eventStatus;
    /**
     * The type of asynchronous event.
     * Possible values: ASSOCIATE, DISASSOCIATE, REVOKE, CREATE, UPDATE, RETIRE
     */
    private String eventType;
    /**
     * Information regarding asynchronous failures.
     */
    private List<ErrorResponse> failures;
    /**
     * The number of completed tasks from the request.
     */
    private Integer numCompleted;
    /**
     * The total number of tasks from the request.
     */
    private Integer numRequested;
    /**
     * The current information for the provided token.
     * The response only includes this field when MDM sets a value using the Client Config endpoint.
     */
    private MdmInfo mdmInfo;
    /**
     * The token expiration date in an ISO-8601 format.
     * Note: The server shows all dates and times in UTC.
     */
    private String tokenExpirationDate;
    /**
     * The unique library identifier. When querying records using multiple tokens that may share libraries,
     * use the uId field to filter duplicates and avoid double-counting records when different content managers upload duplicate tokens.
     */
    private String uId;
}
