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
import org.springframework.web.bind.annotation.RequestMapping;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminDashboardController.class)
public class AdminDashBoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void showAdminDashboardForNonLoggedAdmin() throws Exception {

	mockMvc.perform(get("/adminDashboard"))
	.andExpect(view().name("redirect:/"))
	.andExpect(status().is3xxRedirection());
	}
	@Test
	void showAdminDashboardForLoggedAdmin() throws Exception {
		
		mockMvc.perform(get("/adminDashboard")
				.sessionAttr("adminUserName", "admin"))
		.andExpect(view().name("admin/adminDashboard"))
		.andExpect(status().isOk());
	}
	
	@Test
	void showQuestionModule() throws Exception
	{
		mockMvc.perform(get("/questionModule"))
		.andExpect(view().name("admin/question/questionModule"))
		.andExpect(status().isOk());
	}
	
	@Test 
	void showQuizModule() throws Exception
	{

		mockMvc.perform(get("/quizModule"))
		.andExpect(view().name("admin/quiz/quizModule"))
		.andExpect(status().isOk());
	}
	
}
