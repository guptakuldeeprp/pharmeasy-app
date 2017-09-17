package com.pharmeasy.auth.core;

/**
 * Instead of directly using String as authority, we use Permission class so that we can allow string expressions to be
 * extrapolated into a permission string.
 */
public interface Permission {

    String getPattern();

    boolean matches(Permission permission);

}
