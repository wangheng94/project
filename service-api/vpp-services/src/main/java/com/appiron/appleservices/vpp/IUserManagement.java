package com.appiron.appleservices.vpp;

import java.io.IOException;

/**
 * @author hpg
 * 
 */
public interface IUserManagement {

    GetUsersResponse getUsers(GetUsersRequest request) throws Exception;

    ManageUsersResponse createUsers(ManageUsersRequest request) throws Exception;

    ManageUsersResponse updateUsers(ManageUsersRequest request) throws Exception;

    ManageUsersResponse retireUsers(ManageUsersRequest request) throws Exception;
}
