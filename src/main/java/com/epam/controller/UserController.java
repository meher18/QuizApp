package com.epam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.entity.User;
import com.epam.service.user.UserSignInService;
import com.epam.service.user.UserSignUpService;

@Controller
public class UserController {

	@Autowired
	UserSignInService signInService;

	@Autowired
	UserSignUpService signUpService;

	@RequestMapping(value = "/signInTheUser", params = { "userName", "userPassword" })
	public String userSignIn(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "userPassword") String userPassword, HttpServletRequest request, Model model) {

		String redirect = "redirect:/userSignIn";
		User user = new User();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		User validUser = signInService.validate(user);

		if (validUser != null) {
			HttpSession userSession = request.getSession();
			userSession.setAttribute("userName", validUser.getUserName());
			userSession.setAttribute("userEmail", validUser.getEmail());
			redirect = "redirect:/userModule";
		} else {
			model.addAttribute("userLoginStatus", "invalid credentials");
		}

		return redirect;
	}

	@RequestMapping(value = "/signUpTheUser", params = { "userName", "userEmail", "userPassword" })
	public String userSignUp(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "userEmail") String userEmail,
			@RequestParam(value = "userPassword") String userPassword, HttpServletRequest request, Model model) {

		String redirect = "redirect:/userSignUp";
		User newUser = new User();
		newUser.setUserName(userName);
		newUser.setEmail(userEmail);
		newUser.setUserPassword(userPassword);

		boolean isAlreadyMember = signUpService.checkIfAlreadyMember(newUser);

		if (isAlreadyMember) {
			model.addAttribute("userSignUpStatus", "Already a member");
		} else {

			signUpService.addNewUser(newUser);
			HttpSession userSession = request.getSession();
			userSession.setAttribute("userName", newUser.getUserName());
			userSession.setAttribute("userEmail", newUser.getEmail());
			redirect = "redirect:/userModule";
		}
		return redirect;
	}

	@GetMapping("/userLogout")
	public String logoutUser(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("userName");
		httpSession.removeAttribute("userEmail");

		return "redirect:/";

	}
}
