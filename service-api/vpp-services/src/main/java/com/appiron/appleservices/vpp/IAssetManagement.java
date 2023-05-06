package com.appiron.appleservices.vpp;

import java.io.IOException;

/**
 * @author hpg
 */
public interface IAssetManagement {

    /**
     * Get the set of assets that your organization manages.
     *
     * @param request GetAssetsRequest
     * @return GetAssetsResponse
     */
    GetAssetsResponse getAssets(GetAssetsRequest request) throws Exception;

    /**
     * Use a web service to find details about apps and books to show to your users.
     *
     * @param request AppBookInformationRequest
     * @return AppBookInformationResponse
     */
    AppBookInformationResponse getAppBookInformation(AppBookInformationRequest request) throws Exception;

    /**
     * Associate assets with client user IDs and serial numbers.
     *
     * @param request ManageAssetsRequest
     * @return EventResponse
     */
    EventResponse associateAssets(ManageAssetsRequest request) throws Exception;

    /**
     * Disassociate Assets
     *
     * @param request ManageAssetsRequest
     * @return EventResponse
     */
    EventResponse disassociateAssets(ManageAssetsRequest request) throws Exception;

    /**
     * Revoke assets from client user IDs and serial numbers.
     *
     * @param request RevokeAssetsRequest
     * @return RevokeAssetsResponse
     */
    EventResponse revokeAssets(RevokeAssetsRequest request) throws Exception;

    /**
     * Get the set of current assignments for users or devices.
     *
     * @param request GetAssignmentsRequest
     * @return GetAssignmentsResponse
     */
    GetAssignmentsResponse getAssignments(GetAssignmentsRequest request) throws Exception;
}
