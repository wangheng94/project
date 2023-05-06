package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GetAssignmentsResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * The list of requested assignments.
     */
    @JsonProperty("assignments")
    private List<Assignment> assignments;

    /**
     * The current page index of the paginated response.
     */
    @JsonProperty("currentPageIndex")
    private Integer currentPageIndex;
    /**
     * The next page index in the paginated response.
     * The response only includes this field when there is a next page.
     */
    @JsonProperty("nextPageIndex")
    private Integer nextPageIndex;
    /**
     * The number of assignments on the current page.
     */
    @JsonProperty("size")
    private Integer size;
    /**
     * The total number of pages in the paginated response.
     */
    @JsonProperty("totalPages")
    private Integer totalPages;
    /**
     * The current version identifier. When traversing the paginated response, use versionId to identify when changes occur to underlying data.
     * When any writes occur to the underlying data in a fetch, versionId updates.
     */
    @JsonProperty("versionId")
    private String versionId;
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
