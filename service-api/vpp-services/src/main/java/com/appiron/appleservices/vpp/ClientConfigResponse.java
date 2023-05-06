package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * The response that contains the client configuration.
 *
 * @author hpg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientConfigResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * The ISO alpha-2 country code that designates the organization's location.
     */
    @JsonProperty("countryISO2ACode")
    private String countryISO2ACode;

    /**
     * The value that the MDM client passes for the platform parameter in the contentMetadataLookup request.
     * For more information about how the MDM client queries metadata by using contentMetadataLookup, see Getting App and Book Information.
     * Possible values: volumestore, enterprisestore
     */
    @JsonProperty("defaultPlatform")
    private String defaultPlatform;

    /**
     * The current URL to post notifications to.
     */
    @JsonProperty("notificationUrl")
    private String notificationUrl;

    @JsonProperty("notificationTypes")
    private List<String> notificationTypes;

    /**
     * The set of currently subscribed notification types.
     * Possible values: ASSET_MANAGEMENT, USER_MANAGEMENT, USER_ASSOCIATED, ASSET_COUNT
     */
    @JsonProperty("subscribedNotificationTypes")
    private List<String> subscribedNotificationTypes;

    /**
     * The current website URL for the specified platform.
     * Possible values: https://school.apple.com, https://business.apple.com
     */
    @JsonProperty("websiteURL")
    private String websiteURL;

    /**
     * The current information for the provided token.
     * The response only includes this field when the MDM client sets a value using the Client Config endpoint.
     */
    @JsonProperty("mdmInfo")
    private MdmInfo mdmInfo;

    /**
     * The current name of the library.
     */
    @JsonProperty("notificationAuthToken")
    private String notificationAuthToken;

    /**
     * The token's expiration date in an ISO-8601 format.
     * Note: The server shows all dates and times in UTC.
     */
    @JsonProperty("notificationAuthToken")
    private String tokenExpirationDate;

    /**
     * The unique library identifier. When querying records using multiple tokens that may share libraries,
     * use the uId field to filter duplicates and avoid double-counting records when different content managers upload duplicate tokens.
     */
    @JsonProperty("uId")
    private String uId;

    /**
     * The current name of the library.
     */
    @JsonProperty("locationName")
    private String locationName;
}
