package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showWelcomePage(HttpServletRequest request, Model model) {
		return "index";
	}

	@RequestMapping("/userSignIn")
	public String showUserSignIn(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		String redirectPage = "userSignIn";

		if (httpSession.getAttribute("userName") != null) {
			redirectPage = "redirect:/userModule";
		}
		return redirectPage;
	}

	@RequestMapping("/userSignUp")
	public String showUserSignUp(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		String redirectPage = "userSignUp";

		if (httpSession.getAttribute("userName") != null) {
			redirectPage = "redirect:/userModule";
		}
		return redirectPage;
	}

	@RequestMapping("/admin")
	public String showAdminSignIn(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		String redirectPage = "admin/adminSignIn";

		if (httpSession.getAttribute("adminUserName") != null) {
			redirectPage = "redirect:/adminDashboard";
		}
		return redirectPage;
	}

}
