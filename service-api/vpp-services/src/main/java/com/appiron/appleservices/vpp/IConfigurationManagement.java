package com.appiron.appleservices.vpp;

import java.io.IOException;

/**
 * @author hpg
 * 
 */
public interface IConfigurationManagement {

    ClientConfigResponse clientConfig(ClientConfigRequest request) throws Exception;

    ServiceConfigResponse serviceConfig(int version) throws Exception;
}
