package com.appiron.appleservices.vpp;


import com.appiron.appleservices.common.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hpg
 */
public abstract class Params {

    protected final Map<String, Object> data = new HashMap<>();

    /**
     * 生成json
     */
    public String toJson() {
        return JSON.toJSON(data);
    }

    public Map<String, Object> toParams() {
        return new HashMap<>(data);
    }
}
