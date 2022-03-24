package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserDashboardController {

	@RequestMapping("/userModule")
	public String showUserDashboard(HttpServletRequest request) {

		String redirectPage = "redirect:/";
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("userName") != null) {
			redirectPage = "user/userModule";
		}
		return redirectPage;
	}

	@RequestMapping("/viewParticipation")
	public String viewParticipation(HttpServletRequest request) {

		String redirectPage = "redirect:/";
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("userName") != null) {
//			System.out.println("adfasfd");
			redirectPage = "user/viewParticipation";
		}
		return redirectPage;
	}
}
