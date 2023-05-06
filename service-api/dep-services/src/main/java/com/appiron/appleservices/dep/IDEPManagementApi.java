package com.appiron.appleservices.dep;


/**
 * @author hpg
 */
interface IDEPManagementApi {

    AuthSessionTokenResponse authSessionToken() throws Exception;

    String refreshSessionToken() throws Exception;

    String updateSessionToken(String token);

    String sessionToken() throws Exception;

    AccountResponse account() throws Exception;

    DefineProfileResponse defineProfile(DefineProfileRequest request) throws Exception;

    GetProfileResponse getProfile(GetProfileRequest request) throws Exception;

    AssignProfileResponse assignProfile(ProfileServiceRequest request) throws Exception;

    ClearProfileResponse removeProfile(ClearProfileRequest request) throws Exception;

    ActivationLockResponse activationLockDevices(ActivationLockRequest request) throws Exception;

    DeviceListResponse deviceDetails(DeviceListRequest request) throws Exception;

    FetchDeviceResponse listDevices(FetchDeviceRequest request) throws Exception;

    FetchDeviceResponse syncListDevices(SyncDeviceRequest request) throws Exception;

    DeviceStatusResponse disownDevices(DeviceListRequest request) throws Exception;

}
