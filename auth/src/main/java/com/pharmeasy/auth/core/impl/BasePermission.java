package com.pharmeasy.auth.core.impl;

import com.pharmeasy.auth.core.Permission;

public class BasePermission implements Permission {

    private String permission;

    public BasePermission(String permission) {
        this.permission = permission;
    }

    public String getPattern() {
        return permission;
    }

    @Override
    public boolean matches(Permission permission) {
        return equals(permission);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasePermission that = (BasePermission) o;

        return permission != null ? permission.equals(that.permission) : that.permission == null;
    }

    @Override
    public int hashCode() {
        return permission != null ? permission.hashCode() : 0;
    }
}
