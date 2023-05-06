package com.appiron.appleservices.vpp;

import lombok.Data;

import java.util.List;

/**
 * Information about the error.
 *
 * @author hpg
 */
@Data
public class ResponseErrorInfo {

    /**
     * The requested assets that result in an error.
     */
    private List<Asset> assets;

    /**
     * The requested users that result in an error.
     */
    private List<String> clientUserIds;

    /**
     * The requested devices that result in an error.
     */
    private List<String> serialNumbers;

}
