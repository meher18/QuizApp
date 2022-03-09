package com.epam.service.admin;

import org.springframework.stereotype.Component;

import com.epam.entity.Admin;

@Component
public class AdminService {

    private static final String ADMIN_NAME = "Admin";
    private static final String ADMIN_PASSWORD = "123";

    public boolean checkValidity(Admin admin) {

        Admin currentAdmin = admin;
        boolean isValidAdmin = currentAdmin.getName().equals(ADMIN_NAME) &&
                currentAdmin.getPass().equals(ADMIN_PASSWORD);

        return isValidAdmin;
    }
}
