package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminDashboardController {

	
//	@RequestMapping("/")
//	public String showAdminDashBoard()
//	{
//		return "adminDashBoard";
//	}
	
	@RequestMapping("/questionModule")
	public String showQuestionModule()
	{
		return "admin/question/questionModule";
	}
//	@RequestMapping("/quizModule")
//	public String showQuizModule()
//	{
//		return "adminDashBoard";
//	}
//	@RequestMapping("/questionModule")
//	public String showAdminSettings()
//	{
//		return "adminDashBoard";
//	}

}
