package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.epam.entity.Admin;
import com.epam.service.admin.AdminService;

@Controller
public class AdminLoginController {

	@Autowired
	AdminService adminService;


	@GetMapping("/signInAdmin")
	public String signInAdmin(Admin admin) {

		String redirectPage = "admin/adminSignIn";
		if (adminService.checkValidity(admin)) {
			redirectPage = "admin/adminDashboard";
		}
		return redirectPage;
	}

	@GetMapping("/adminLogout")
	public String logoutAdmin(Admin admin) {

		return "redirect:/admin";

	}
}
