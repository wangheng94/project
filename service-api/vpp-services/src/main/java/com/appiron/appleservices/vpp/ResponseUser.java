package com.appiron.appleservices.vpp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author hpg
 */
@Data
public class ResponseUser {
    /**
     * The unique identifier for a user in your organization.
     */
    @JsonProperty("clientUserId")
    private String clientUserId;

    /**
     * The user’s email address.
     */
    @JsonProperty("email")
    private String email;


    /**
     * The hash of the user’s unique store identifier. The idHash field is only present when the user has an associated Apple ID.
     */
    @JsonProperty("idHash")
    private String idHash;


    /**
     * The invitation code that associates an Apple ID to a user.
     */
    @JsonProperty("inviteCode")
    private String inviteCode;

    /**
     * The current status of the user in the organization.
     * Possible values: Registered, Associated, Retired, Deleted
     */
    @JsonProperty("status")
    private String status;
}
