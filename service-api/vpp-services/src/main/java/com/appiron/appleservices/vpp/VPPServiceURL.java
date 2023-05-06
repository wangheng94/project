package com.appiron.appleservices.vpp;

/**
 * @author hpg
 * 
 */
public class VPPServiceURL {

    public static class VERSION_1 {
        public static final String getUsersSrvUrl = "https://vpp.itunes.apple.com/mdm/getVPPUsersSrv";
        public static final String getLicensesSrvUrl = "https://vpp.itunes.apple.com/mdm/getVPPLicensesSrv";
        public static final String editUserSrvUrl = "https://vpp.itunes.apple.com/mdm/editVPPUserSrv";
        public static final String retireUserSrvUrl = "https://vpp.itunes.apple.com/mdm/retireVPPUserSrv";
        public static final String getAssignmentsSrvUrl = "https://vpp.itunes.apple.com/mdm/getAssignments";
        public static final String disassociateLicenseSrvUrl = "https://vpp.itunes.apple.com/mdm/disassociateVPPLicenseSrv";
        public static final String registerUserSrvUrl = "https://vpp.itunes.apple.com/mdm/registerVPPUserSrv";
        public static final String associateLicenseSrvUrl = "https://vpp.itunes.apple.com/mdm/associateVPPLicenseSrv";
        public static final String getVPPAssetsSrvUrl = "https://vpp.itunes.apple.com/mdm/getVPPAssetsSrv";
        public static final String vppWebsiteUrl = "https://vpp.itunes.apple.com/";
        public static final String getUserSrvUrl = "https://vpp.itunes.apple.com/mdm/getVPPUserSrv";
        public static final String contentMetadataLookupUrl = "https://uclient-api.itunes.apple.com/WebObjects/MZStorePlatform.woa/wa/lookup";
        public static final String clientConfigSrvUrl = "https://vpp.itunes.apple.com/mdm/VPPClientConfigSrv";
        public static final String manageVPPLicensesByAdamIdSrvUrl = "https://vpp.itunes.apple.com/mdm/manageVPPLicensesByAdamIdSrv";
        public static final String invitationEmailUrl = "https://buy.itunes.apple.com/WebObjects/MZFinance.woa/wa/associateVPPUserWithITSAccount?cc=us&inviteCode=%25inviteCode%25&mt=8";
    }

    public static class VERSION_2 {
        public static final String CLIENT_CONFIG = "https://vpp.itunes.apple.com/mdm/v2/client/config";
        public static final String SERVICE_CONFIG = "https://vpp.itunes.apple.com/mdm/v2/service/config";
        public static final String CONTENT_META_DATA_LOOKUP = "https://uclient-api.itunes.apple.com/WebObjects/MZStorePlatform.woa/wa/lookup";
        public static final String ASSETS = "https://vpp.itunes.apple.com/mdm/v2/assets";
        public static final String ASSOCIATE_ASSETS = "https://vpp.itunes.apple.com/mdm/v2/assets/associate";
        public static final String DISASSOCIATE = "https://vpp.itunes.apple.com/mdm/v2/assets/disassociate";
        public static final String REVOKE_ASSETS = "https://vpp.itunes.apple.com/mdm/v2/assets/revoke";
        public static final String ASSIGNMENTS = "https://vpp.itunes.apple.com/mdm/v2/assignments";
        public static final String USERS = "https://vpp.itunes.apple.com/mdm/v2/users";
        public static final String CREATE_USERS = "https://vpp.itunes.apple.com/mdm/v2/users/create";
        public static final String UPDATE_USERS = "https://vpp.itunes.apple.com/mdm/v2/users/update";
        public static final String RETIRE_USERS = "https://vpp.itunes.apple.com/mdm/v2/users/retire";
        public static final String STATUS = "https://vpp.itunes.apple.com/mdm/v2/status";
    }


}
