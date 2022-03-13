package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminDashboardController {

	
	@RequestMapping("/adminDashboard")
	public String showAdminDashBoard()
	{
		return "admin/adminDashboard";
	}
	
	@RequestMapping("/questionModule")
	public String showQuestionModule()
	{
		return "admin/question/questionModule";
	}
	@RequestMapping("/quizModule")
	public String showQuizModule()
	{
		return "admin/quiz/quizModule";
	}
//	@RequestMapping("/questionModule")
//	public String showAdminSettings()
//	{
//		return "adminDashBoard";
//	}

}
