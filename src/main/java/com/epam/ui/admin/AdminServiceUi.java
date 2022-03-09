package com.epam.ui.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.config.AppContext;
import com.epam.entity.Admin;
import com.epam.global.CorrectInput;
import com.epam.service.admin.AdminService;

@Component
public class AdminServiceUi {


    public static final Logger LOGGER = LogManager.getLogger(AdminServiceUi.class);

    public Admin getAdminInput() {
        Admin admin = new Admin();
        LOGGER.info("- ADMIN LOGIN ");
        LOGGER.info("Enter Admin user name: ");
        String adminUserName = CorrectInput.getString();
        LOGGER.info("Enter Admin password: ");
        String adminPassword = CorrectInput.getString();
        admin.setName(adminUserName);
        admin.setPass(adminPassword);
        return admin;
    }

    public void checkAdmin() {
        Admin currentAdmin = getAdminInput();
        AdminService adminService = AppContext.getApplicationContext().getBean(AdminService.class);
        boolean isValidAdmin = adminService.checkValidity(currentAdmin);
        if (isValidAdmin) {
            AdminDashBoardUi.showAdminTasks();
        } else {
            LOGGER.warn("..Invalid credentials..");
            App.startApplication();
        }
    }
}
