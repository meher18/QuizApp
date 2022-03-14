package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.epam.entity.Admin;
import com.epam.service.admin.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/signInAdmin")
	public String signInAdmin(Admin admin, HttpServletRequest request, Model model) {

		String adminLoginStatus = "INVALID CREDENTIALS";
		String redirectPage = "redirect:/admin";
		
		if (adminService.checkValidity(admin)) {
			
			adminLoginStatus = "LOGIN SUCCESSFUL";
			HttpSession session = request.getSession();
			session.setAttribute("adminUserName", admin.getName());
			redirectPage = "redirect:/adminDashboard";
		}
		model.addAttribute("adminLoginStatus", adminLoginStatus);
		return redirectPage;
	}

	@GetMapping("/adminLogout")
	public String logoutAdmin(Admin admin, HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("adminUserName");
		
		return "redirect:/";

	}
}
