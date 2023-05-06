package com.appiron.appleservices.dep;

/**
 * @author hpg
 * 
 */
public interface IEscrowKeyUnlockService {

    EscrowKeyUnlockStatusResponse escrowKeyUnlock(String code, String escrowKey,
                                                  String orgName, String guid,
                                                  String serialNumber, String productType,
                                                  String imei1, String imei2, String meid) throws Exception;
}
