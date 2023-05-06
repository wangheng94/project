package com.appiron.appleservices.dep;

/**
 * @author hpg
 */
public class DEPRequest50xException extends DEPRequestException {

    public DEPRequest50xException(Error error) {
        super(error);
    }
}
