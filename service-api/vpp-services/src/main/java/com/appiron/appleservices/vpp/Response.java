package com.appiron.appleservices.vpp;


import com.appiron.appleservices.common.JSON;

import java.util.LinkedHashMap;

/**
 * <p>
 * ResponseBody see  https://developer.apple.com/documentation/devicemanagement/getassignmentsresponse
 * </p>
 *
 * @author hpg
 * @date 2023/2/10 13:39
 */
public abstract class Response extends LinkedHashMap<String, Object> {

    public final String toJson() {
        return JSON.toJSON(this);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
