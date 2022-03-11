package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showWelcomePage()
	{
		return "index";
	}
	
	@RequestMapping("/userSignIn")
	public String showUserSignIn()
	{
		return "userSignIn";
	}
	@RequestMapping("/userSignUp")
	public String showUserSignUp()
	{
		return "userSignUp";
	}
	
	@RequestMapping("/admin")
	public String showAdminSignIn()
	{
		return "/admin/adminSignIn";
	}
	
}
