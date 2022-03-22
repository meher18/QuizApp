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
@WebMvcTest(UserDashboardController.class)
 class UserDashboardControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void showUserDashboardForLoggedUser() throws Exception{
		mockMvc.perform(get("/userModule"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection());
	}
	@Test
	void showUserDashboardForNonLoggedUser() throws Exception{
		mockMvc.perform(get("/userModule")
			.sessionAttr("userName", "user"))
		.andExpect(view().name("user/userModule"))
		.andExpect(status().isOk());
	}
	

	@Test
	void showUserParitcipationForLoggedUser() throws Exception{
		mockMvc.perform(get("/viewParticipation"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection());
	}
	@Test
	void showUserParticipationForNonLoggedUser() throws Exception{
		mockMvc.perform(get("/viewParticipation")
			.sessionAttr("userName", "user"))
		.andExpect(view().name("user/viewParticipation"))
		.andExpect(status().isOk());
	}
}
