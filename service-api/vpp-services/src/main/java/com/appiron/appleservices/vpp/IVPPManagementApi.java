package com.appiron.appleservices.vpp;


import java.io.IOException;

/**
 * @author hpg
 * 
 */
interface IVPPManagementApi {

    Object getUsersSrv() throws IOException;

    Object getLicensesSrvUrl() throws IOException;

    Object editUserSrv() throws IOException;

    Object retireUserSrv() throws IOException;

    Object getAssignmentsSrv() throws IOException;

    Object disassociateLicenseSrv() throws IOException;

    Object registerUserSrv() throws IOException;

    Object associateLicenseSrv() throws IOException;

    Object getVPPAssetsSrv() throws IOException;

    Object vppWebsite() throws IOException;

    Object getUserSrv() throws IOException;

    Object contentMetadataLookup(int version, String id, String p, String caller, String platform, String cc, String l) throws IOException;

    Object clientConfigSrv() throws IOException;

    Object manageVPPLicensesByAdamIdSrv() throws IOException;

    Object invitationEmail() throws IOException;
}
