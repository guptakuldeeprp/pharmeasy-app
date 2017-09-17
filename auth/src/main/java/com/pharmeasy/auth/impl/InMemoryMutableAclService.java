package com.pharmeasy.auth.impl;

import com.pharmeasy.auth.MutableAclService;
import com.pharmeasy.auth.UserContext;
import com.pharmeasy.auth.core.AclEntry;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.ex.AclDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryMutableAclService extends InMemoryAclService implements MutableAclService {

    public InMemoryMutableAclService(Map<Resource, List<AclEntry>> resoulceAclMap) {
        super(resoulceAclMap);
    }

    @Override
    public void insertAclEntry(AclEntry entry) {
        checkOwner(entry);
        if (entry.getResource() == null || entry.getResource().getId() == null)
            throw new AclDataException("Invalid resource null or empty");
        if (entry.getUser() == null || entry.getUser().getId() == null)
            throw new AclDataException("Invalid user null or empty");
        resoulceAclMap.putIfAbsent(entry.getResource(), new ArrayList<>());
        resoulceAclMap.get(entry).add(entry);
    }

    @Override
    public void insertAcl(List<AclEntry> entries) {
        checkOwner(entries);
        for (AclEntry entry : entries)
            insertAclEntry(entry);
    }

    @Override
    public void grantAclEntryPermission(AclEntry entry) {
        checkOwner(entry);
    }

    @Override
    public void revokeAclEntryPermission(AclEntry entry) {
        checkOwner(entry);

    }

    protected void checkOwner(List<AclEntry> entries) throws AclDataException {
        for (AclEntry entry : entries)
            if (!entry.getOwner().equals(UserContext.getUser()))
                throw new AclDataException("only owner can manipulate acl");
    }

    protected void checkOwner(AclEntry entry) throws AclDataException {
        if (!entry.getOwner().equals(UserContext.getUser()))
            throw new AclDataException("only owner can manipulate acl");
    }
}
