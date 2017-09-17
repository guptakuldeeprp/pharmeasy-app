package com.pharmeasy.auth.core;

import java.io.Serializable;

public interface User extends Typed, Serializable {

    Serializable getId();

    boolean equals(Object obj);

    String getUsername();

    int hashCode();

}
