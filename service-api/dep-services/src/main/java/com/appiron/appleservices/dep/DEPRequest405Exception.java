package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public class DEPRequest405Exception extends DEPRequestException {

    public DEPRequest405Exception(Error error) {
        super(error);
    }

    public DEPRequest405Exception(String error) {
        super(error);
    }
}
