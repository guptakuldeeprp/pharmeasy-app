package com.pharmeasy.auth.core;

import com.sun.org.apache.regexp.internal.RE;

import java.io.Serializable;

public class AclEntry implements Serializable {

    Long id;
    User user;
    User owner;
    Resource resource;
    Permission permission;
    boolean granted;

    public AclEntry() {

    }

    public AclEntry(Long id, User user, Resource resource, Permission permission, boolean granted) {
        this.id = id;
        this.user = user;
        this.resource = resource;
        this.permission = permission;
        this.granted = granted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }
}
