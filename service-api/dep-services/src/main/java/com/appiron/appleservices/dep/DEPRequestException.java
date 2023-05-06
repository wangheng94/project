package com.appiron.appleservices.dep;

/**
 * @author hpg
 */
public class DEPRequestException extends RuntimeException {

    public DEPRequestException(Error error) {
        super("\n" + error.toString());
    }

    public DEPRequestException(String error) {
        super(error);
    }
}
