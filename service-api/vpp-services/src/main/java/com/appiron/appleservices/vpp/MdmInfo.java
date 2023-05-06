package com.appiron.appleservices.vpp;

import lombok.Data;

/**
 * Information about the MDM client.
 *
 * @author hpg
 */
@Data
public class MdmInfo {

    /**
     * A unique identifier that MDM uses for an organization.
     */
    private String id;

    /**
     * A free-form field that MDM uses to store metadata for an organization.
     */
    private String metadata;

    /**
     * The name of the current MDM client.
     */
    private String name;
}
