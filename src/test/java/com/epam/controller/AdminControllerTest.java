package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.AdminDto;
import com.epam.entity.Admin;
import com.epam.service.admin.AdminService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminService adminService;

	@Test
	void signInValidAdmin() throws Exception {

		AdminDto adminDto = new AdminDto();
		adminDto.setName("admin");
		adminDto.setPass("pass");

		when(adminService.checkValidity(any(AdminDto.class))).thenReturn(true);
		mockMvc.perform(get("/signInAdmin").param("name", adminDto.getName()).param("pass", adminDto.getPass()))
				.andExpect(view().name("redirect:/adminDashboard")).andExpect(status().is3xxRedirection());
	}

	@Test
	void signInInValidAdmin() throws Exception {

		Admin admin = new Admin();
		admin.setName("admin");
		admin.setPass("pass");

		when(adminService.checkValidity(any(AdminDto.class))).thenReturn(false);
		mockMvc.perform(get("/signInAdmin").param("name", admin.getName()).param("pass", admin.getPass()))
				.andExpect(view().name("redirect:/admin")).andExpect(status().is3xxRedirection());
	}

	@Test
	void logoutAdmin() throws Exception {

		Admin admin = new Admin();
		admin.setName("admin");
		admin.setPass("pass");

		mockMvc.perform(get("/adminLogout").param("name", admin.getName()).param("pass", admin.getPass())
				.sessionAttr("adminUserName", admin.getName())).andExpect(view().name("redirect:/"));
	}

}
