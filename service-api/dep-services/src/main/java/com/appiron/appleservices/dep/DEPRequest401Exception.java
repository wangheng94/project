package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public class DEPRequest401Exception extends DEPRequestException {

    public DEPRequest401Exception(Error error) {
        super(error);
    }

    public DEPRequest401Exception(String error) {
        super(error);
    }
}
