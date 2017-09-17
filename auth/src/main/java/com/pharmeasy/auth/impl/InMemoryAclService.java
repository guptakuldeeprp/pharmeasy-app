package com.pharmeasy.auth.impl;

import com.pharmeasy.auth.AclService;
import com.pharmeasy.auth.core.AclEntry;
import com.pharmeasy.auth.core.Resource;
import com.pharmeasy.auth.core.User;
import com.pharmeasy.auth.ex.AclDataException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryAclService implements AclService {

    protected final ConcurrentHashMap<Resource, List<AclEntry>> resoulceAclMap;

    public InMemoryAclService(Map<Resource, List<AclEntry>> resoulceAclMap) {
        this.resoulceAclMap = new ConcurrentHashMap<>(resoulceAclMap);
    }

    @Override
    public List<AclEntry> getAclFor(Resource resource) throws AclDataException {
        if (!resoulceAclMap.containsKey(resource))
            throw new AclDataException("no acl found for resource " + resource);
        List<AclEntry> allAclEntries = resoulceAclMap.get(resource);
        return Collections.unmodifiableList(
                allAclEntries
                        .stream()
                        .filter(aclEntry -> aclEntry.isGranted())
                        .collect(Collectors.toList()));
    }

    @Override
    public List<AclEntry> getAclFor(Resource resource, User user) {
        List<AclEntry> resourceAcl = getAclFor(resource);
        return Collections.unmodifiableList(
                resourceAcl
                        .stream()
                        .filter(aclEntry -> aclEntry.getUser().getUsername().equals(user.getUsername()))
                        .collect(Collectors.toList()));
    }

    @Override
    public List<AclEntry> getPendingAcl(Resource resource) {
        if (!resoulceAclMap.containsKey(resource))
            throw new AclDataException("no acl found for resource " + resource);
        List<AclEntry> allAclEntries = resoulceAclMap.get(resource);
        return Collections.unmodifiableList(
                allAclEntries
                        .stream()
                        .filter(aclEntry -> !aclEntry.isGranted())
                        .collect(Collectors.toList()));
    }

    @Override
    public List<AclEntry> getPendingAcl(Resource resource, User user) {
        List<AclEntry> resourceAcl = getPendingAcl(resource);
        return Collections.unmodifiableList(
                resourceAcl
                        .stream()
                        .filter(aclEntry -> aclEntry.getUser().getUsername().equals(user.getUsername()))
                        .collect(Collectors.toList()));
    }

    @Override
    public List<AclEntry> getPendingAcl(User owner) {
        return resoulceAclMap
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(aclEntry -> !aclEntry.isGranted() && aclEntry.getOwner().getId().equals(owner.getId()))
                .collect(Collectors.toList());
    }
}
