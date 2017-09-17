package com.pharmeasy.auth.ex;

public class AclDataException extends RuntimeException{

    public AclDataException(String message) {
        super(message);
    }

    public AclDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
