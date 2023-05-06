package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.api.HttpTransport;

/**
 * @author hpg
 * 
 */
public class DEPManagementApi extends AbstractDEPManagementApi {

    public DEPManagementApi(DEPManagement depManagement, HttpTransport transport) {
        super(depManagement, transport);
    }
}
