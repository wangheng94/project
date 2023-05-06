package com.appiron.appleservices.vpp;

import java.io.IOException;

/**
 * @author hpg
 * 
 */
public interface IEventManagement {

    StatusResponse eventStatus(String eventId) throws Exception;
}
