package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public class DEPRequest404Exception extends DEPRequestException {

    public DEPRequest404Exception(Error error) {
        super(error);
    }

    public DEPRequest404Exception(String error) {
        super(error);
    }
}
