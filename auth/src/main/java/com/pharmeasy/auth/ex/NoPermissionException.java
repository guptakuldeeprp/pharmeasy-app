package com.pharmeasy.auth.ex;

public class NoPermissionException extends RuntimeException {

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
