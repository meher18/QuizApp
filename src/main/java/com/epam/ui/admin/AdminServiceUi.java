package com.epam.ui.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.entity.Admin;
import com.epam.service.admin.AdminService;
import com.epam.util.CorrectInput;

@Component
public class AdminServiceUi {

	public static final Logger LOGGER = LogManager.getLogger(AdminServiceUi.class);

	@Autowired
	Admin admin;

	@Autowired
	AdminService adminService;

	
	@Autowired
	AdminDashBoardUi adminDashBoardUi;
	
	public Admin getAdminInput() {
		
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
		boolean isValidAdmin = adminService.checkValidity(currentAdmin);
		if (isValidAdmin) {
			adminDashBoardUi.showAdminTasks();
		} else {
			LOGGER.warn("..Invalid credentials..");
			App.startApplication();
		}
	}
}
