package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminDashboardController {

	@RequestMapping("/adminDashboard")
	public String showAdminDashBoard(HttpServletRequest request) {

		String redirectPage = "redirect:/";
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("adminUserName") != null) {
			redirectPage = "admin/adminDashboard";
		}
		return redirectPage;
	}

	@RequestMapping("/questionModule")
	public String showQuestionModule() {
		return "admin/question/questionModule";
	}

	@RequestMapping("/quizModule")
	public String showQuizModule() {
		return "admin/quiz/quizModule";
	}


}
