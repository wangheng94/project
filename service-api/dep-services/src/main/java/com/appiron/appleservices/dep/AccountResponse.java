package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountResponse extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * An identifiable name for the MDM server.
     */
    @JsonProperty("server_name")
    private String serverName;

    /**
     * A system-generated server identifier.
     */
    @JsonProperty("server_uuid")
    private String serverUuid;

    /**
     * Legacy equivalent to the admin_id key. This key is deprecated and may not be returned
     * in future responses.
     *
     * @deprecated adminId
     */
    @Deprecated
    @JsonProperty("facilitator_id")
    private String facilitatorId;

    /**
     * Apple ID of the person who generated the current tokens that are in use
     */
    @JsonProperty("admin_id")
    private String adminId;

    /**
     * The organization name.
     */
    @JsonProperty("org_name")
    private String orgName;

    /**
     * The organization email address.
     */
    @JsonProperty("org_email")
    private String orgEmail;

    /**
     * The organization phone.
     */
    @JsonProperty("org_phone")
    private String orgPhone;

    /**
     * The organization address.
     */
    @JsonProperty("org_address")
    private String orgAddress;

    /**
     * DEP customer ID. This key is available only in protocol version 3 and later
     */
    @JsonProperty("org_id")
    private String orgId;

    /**
     * Returns the SHA hash of an org identifier. This helps MDMs match it with the
     * organizationIdHash key in the VPPClientConfigSrv API. This key is available only
     * in protocol version 3 and later.
     */
    @JsonProperty("org_id_hash")
    private String orgIdHash;

    /**
     * Possible values: edu or org. This key is available only in protocol version 3 and later.
     */
    @JsonProperty("org_type")
    private String orgType;

    /**
     * Possible values: v1 or v2. v1 is for ADP organizations and v2 is for ASM organizations.
     * Currently v2 is applicable only to educational organizations. This key is available only in
     * protocol version 3 and later.
     */
    @JsonProperty("org_version")
    private String orgVersion;

    /**
     * The list of dictionaries (see below) containing URLs available in MDM service. This key is
     * valid in X-Server-Protocol-Version 3 and later.
     */
    @JsonProperty("urls")
    private List<Url> urls;
}
