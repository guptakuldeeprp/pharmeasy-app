package com.pharmeasy.auth.util;

import com.pharmeasy.auth.core.Permission;
import com.pharmeasy.auth.core.impl.BasePermission;

public class BasicPermissions {

    public static Permission READ = new BasePermission("R");
    public static Permission WRITE = new BasePermission("W");
    public static Permission CREATE = new BasePermission("C");
    public static Permission DELETE = new BasePermission("D");

}
