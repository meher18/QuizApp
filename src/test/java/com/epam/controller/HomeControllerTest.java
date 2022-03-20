package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
 class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void showWelcomePage() throws Exception
	{
		mockMvc.perform(get("/"))
		.andExpect(view().name("index"))
		.andExpect(status().isOk());
	}
	
	@Test
	void showUserSignInForAlreadyLoggedUser() throws Exception
	{
		mockMvc.perform(get("/userSignIn")
				.sessionAttr("userName", "user"))
		.andExpect(view().name("redirect:/userModule"))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	void showUserSignInForNonLoggedUser() throws Exception
	{
		mockMvc.perform(get("/userSignIn"))
		.andExpect(view().name("userSignIn"))
		.andExpect(status().isOk());
	}
	
	@Test
	void showUserSignUpForLoggedUser() throws Exception{
		mockMvc.perform(get("/userSignUp"))
		.andExpect(view().name("userSignUp"))
		.andExpect(status().isOk());
	}
	@Test
	void showUserSignUpForNonLoggedUser() throws Exception{
		mockMvc.perform(get("/userSignUp")
				.sessionAttr("userName", "user"))
		.andExpect(view().name("redirect:/userModule"))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	void showAdminSignInForLoggedAdmin() throws Exception
	{
		mockMvc.perform(get("/admin")
				.sessionAttr("adminUserName", "admin"))
		.andExpect(view().name("redirect:/adminDashboard"))
		.andExpect(status().is3xxRedirection());
	}
	@Test
	void showAdminSignInForNonLoggedAdmin() throws Exception
	{
		mockMvc.perform(get("/admin"))
		.andExpect(view().name("admin/adminSignIn"))
		.andExpect(status().isOk());
	}
}
