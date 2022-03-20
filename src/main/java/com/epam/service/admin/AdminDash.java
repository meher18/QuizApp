package com.epam.service.admin;

import org.springframework.stereotype.Component;

@Component
public class AdminDash {
    private AdminDash()
    {
    	
    }
    public static boolean logoutAdmin() {
        return true;
    }
}
