package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public class DEPRequest403Exception extends DEPRequestException {

    public DEPRequest403Exception(Error error) {
        super(error);
    }

    public DEPRequest403Exception(String error) {
        super(error);
    }
}
