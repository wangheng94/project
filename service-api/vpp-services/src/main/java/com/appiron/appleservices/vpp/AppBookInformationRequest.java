package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.GenerationConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * version=2&id=361309726&p=mdm-lockup&caller=MDM&platform=enterprisestore&cc=us&l=en
 *
 * @author hpg
 */
@Data
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AppBookInformationRequest extends GenerationConvert implements Serializable {

    private static final long serialVersionUID = 42L;

    public AppBookInformationRequest() {
    }

    public AppBookInformationRequest(String id) {
        this.data.put("id", id);
        this.data.put("version", 2);
        this.data.put("p", "mdm-lockup");
        this.data.put("caller", "MDM");
        this.data.put("platform", "enterprisestore");
        this.data.put("cc", "us");
        this.data.put("l", "en");
    }
}
