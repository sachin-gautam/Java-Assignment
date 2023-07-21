package com.javassignment.wishlist.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password){
        boolean isValidUsername = username.equalsIgnoreCase("sachin");
        boolean isValidPassword = password.equalsIgnoreCase("flyboy");
        return isValidPassword && isValidUsername;
    }
}
