package com.pharmeasy.auth;

import com.pharmeasy.auth.core.AclEntry;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.core.User;

import java.util.List;

public interface AclService {

    List<AclEntry> getAclFor(Resource resource);

    List<AclEntry> getAclFor(Resource resource, User user);

    List<AclEntry> getPendingAcl(Resource resource);

    List<AclEntry> getPendingAcl(Resource resource, User user);

    List<AclEntry> getPendingAcl(User owner);

}
