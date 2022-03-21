package com.epam.service.admin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.AdminDto;
import com.epam.entity.Admin;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

	private AdminService adminService;

	@BeforeEach
	void init() {
		adminService = new AdminService();
		adminService.mapper = new ModelMapper();
	}

	@Test
	void checkValidity() {
		Admin admin = new Admin();
		admin.setName("Admin");
		admin.setPass("123");

		AdminDto adminDto = new AdminDto();
		adminDto.setName("Admin");
		adminDto.setPass("123");

		assertTrue(adminService.checkValidity(adminDto));
	}

}
