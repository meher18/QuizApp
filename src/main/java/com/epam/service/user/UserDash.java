package com.epam.service.user;

import org.springframework.stereotype.Component;

@Component
public class UserDash {
    private UserDash(){}
    public static boolean logoutUser() {
        return true;
    }
}
