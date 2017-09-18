package com.pharmeasy.auth;

import com.pharmeasy.auth.core.User;

import java.io.Serializable;

public class UserContext {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<User>();


    public static void setUser(User user) {
        if (user == null) throw new NullPointerException("user cannot be null");
        userHolder.set(user);
    }

    public static void clearUser() {
        userHolder.remove();
    }

    public static User getUser() {
        if (userHolder.get() == null)
            setUser(getEmptyUser());
        return userHolder.get();
    }

    private static User getEmptyUser() {
        return new User() {
            @Override
            public Serializable getId() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public String getType() {
                return "default";
            }
        };
    }


}
