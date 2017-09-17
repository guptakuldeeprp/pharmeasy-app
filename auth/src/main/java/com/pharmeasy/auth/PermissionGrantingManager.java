package com.pharmeasy.auth;

import com.pharmeasy.auth.core.AclEntry;
import com.pharmeasy.auth.core.Permission;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.core.User;

import java.util.List;

public interface PermissionGrantingManager {

    boolean isGranted(User user, Resource resource, Permission requiredPermission);

}
