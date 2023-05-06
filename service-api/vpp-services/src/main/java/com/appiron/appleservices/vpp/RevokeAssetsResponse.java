package com.appiron.appleservices.vpp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author hpg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RevokeAssetsResponse extends EventResponse implements Serializable {

    private static final long serialVersionUID = 42L;

}
