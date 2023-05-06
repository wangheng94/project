package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.GenerationConvert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hpg
 * 
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ActivationLockRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * Serial number of the device (required).
     */
    @JsonProperty("device")
    private String device;


    /**
     * Escrow key (optional). If the escrow key is not provided,
     * the device will be locked to the person who created the MDM server in the portal.
     * For information about creating an escrow key see Creating and Using Bypass Codes.
     */
    @JsonProperty("escrow_key")
    private String escrowKey;


    /**
     * Lost message to be displayed on the device (optional).
     */
    
    @JsonProperty("lost_message")
    private String lostMessage;
}
