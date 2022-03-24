package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.dto.AdminDto;
import com.epam.service.admin.AdminService;
import com.epam.util.Constants;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	public static final String ADMIN_LOGIN_STATUS = "adminLoginStatus";

	@GetMapping("/signInTheAdmin")
	public String signInAdmin(AdminDto adminDto, HttpServletRequest request, RedirectAttributes attributes) {

		String adminLoginStatus = Constants.INVALID_CREDENTIALS;
		String redirectPage = "redirect:/admin";

		if (adminService.checkValidity(adminDto)) {

			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_ADMIN_USER_NAME, adminDto.getName());
			adminLoginStatus = Constants.LOGIN_SUCCESS;
			redirectPage = "redirect:/adminDashboard";
		}
		attributes.addFlashAttribute(ADMIN_LOGIN_STATUS, adminLoginStatus);
		return redirectPage;
	}

	@GetMapping("/adminLogout")
	public String logoutAdmin(AdminDto adminDto, HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute(Constants.SESSION_ADMIN_USER_NAME);
		
		return "redirect:/";

	}
}
