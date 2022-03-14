package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showWelcomePage(HttpServletRequest request,  Model model)
	{
		String redirectPage = "index";
	
		return redirectPage;
	}
	
	@RequestMapping("/userSignIn")
	public String showUserSignIn(HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String redirectPage = "user/userSignIn";
		
		if(httpSession.getAttribute("userName") != null)
		{
			redirectPage = "redirect:/userDashboard";
		}
		return redirectPage;
	}
	@RequestMapping("/userSignUp")
	public String showUserSignUp()
	{
		return "userSignUp";
	}
	
	@RequestMapping("/admin")
	public String showAdminSignIn(HttpServletRequest request)
	{

		HttpSession httpSession = request.getSession();
		String redirectPage = "admin/adminSignIn";
		
		if(httpSession.getAttribute("adminUserName") != null)
		{
			redirectPage = "redirect:/adminDashboard";
		}
		return redirectPage;
	}
	
}
