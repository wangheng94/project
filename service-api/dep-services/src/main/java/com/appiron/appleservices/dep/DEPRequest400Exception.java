package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public class DEPRequest400Exception extends DEPRequestException {

    public DEPRequest400Exception(Error error) {
        super(error);
    }
}
