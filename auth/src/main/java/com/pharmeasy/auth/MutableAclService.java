package com.pharmeasy.auth;

import com.pharmeasy.auth.core.AclEntry;

import java.util.List;

public interface MutableAclService {

    void insertAclEntry(AclEntry entry);

    void insertAcl(List<AclEntry> entries);

    void grantAclEntryPermission(AclEntry entry);

    void revokeAclEntryPermission(AclEntry entry);

}
