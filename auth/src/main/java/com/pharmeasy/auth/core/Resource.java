package com.pharmeasy.auth.core;

import java.io.Serializable;

public interface Resource extends Typed, Serializable {

    public Serializable getId();

    boolean equals(Object object);

    int hashCode();

    // get set of valid permissions for a resource

}
