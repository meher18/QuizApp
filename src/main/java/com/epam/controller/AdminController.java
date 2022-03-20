package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.dto.AdminDto;
import com.epam.service.admin.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/signInTheAdmin")
	public String signInAdmin(AdminDto adminDto, HttpServletRequest request, RedirectAttributes attributes) {

		String adminLoginStatus = "INVALID CREDENTIALS";
		String redirectPage = "redirect:/admin";

		if (adminService.checkValidity(adminDto)) {

			adminLoginStatus = "LOGIN SUCCESSFUL";
			HttpSession session = request.getSession();
			session.setAttribute("adminUserName", adminDto.getName());
			redirectPage = "redirect:/adminDashboard";
		}
		attributes.addFlashAttribute("adminLoginStatus", adminLoginStatus);

		return redirectPage;
	}

	@GetMapping("/adminLogout")
	public String logoutAdmin(AdminDto adminDto, HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("adminUserName");

		return "redirect:/";

	}
}
