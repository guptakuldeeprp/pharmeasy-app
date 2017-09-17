package com.pharmeasy.auth;

import com.pharmeasy.auth.core.AclEntry;
import com.pharmeasy.auth.core.Permission;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.core.User;
import com.pharmeasy.auth.ex.AccessDeniedException;
import com.pharmeasy.auth.ex.NoPermissionException;

import java.util.List;

public class DefaultPermissionGrantingManager implements PermissionGrantingManager {

    private final AclService aclService;

    public DefaultPermissionGrantingManager(AclService aclService) {
        this.aclService = aclService;
    }

    @Override
    public boolean isGranted(User user, Resource resource, Permission requiredPermission) {
        if (user == null || user.getId() == null) throw new NoPermissionException("user/requester is null");
        if (requiredPermission == null) throw new NoPermissionException("No permission specified");
        if (resource == null) throw new AccessDeniedException("No resource specified");
        List<AclEntry> acl = aclService.getAclFor(resource, UserContext.getUser());
        if (acl == null || acl.isEmpty())
            return false;//throw new AccessDeniedException("User has not acl entry for the requested resource");
        for (AclEntry aclEntry : acl) {
            if (
                    aclEntry.getResource().getId().equals(resource.getId())
                    && aclEntry.getPermission().matches(requiredPermission)
                    && aclEntry.isGranted())
                return true;
        }

        //if(UserContext)
        //if(resource.)
        return false;
    }
}
