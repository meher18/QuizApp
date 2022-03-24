package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.util.Constants;

@Controller
public class AdminDashboardController {

	@RequestMapping("/adminDashboard")
	public String showAdminDashBoard(HttpServletRequest request) {

		String redirectPage = "redirect:/";
		HttpSession httpSession = request.getSession();

		//  checking for session
		if (httpSession.getAttribute(Constants.SESSION_ADMIN_USER_NAME) != null) {
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
