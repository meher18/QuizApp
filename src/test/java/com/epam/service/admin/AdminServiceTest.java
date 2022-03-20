package com.epam.service.admin;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.dto.AdminDto;
import com.epam.entity.Admin;

@ExtendWith(SpringExtension.class)
class AdminServiceTest {

	@Mock
	AdminService adminService;

	@Mock
	ModelMapper mapper;

	@Test
	void checkValidity() {
		Admin admin = new Admin();
		admin.setName("Admin");
		admin.setPass("123");

		AdminDto adminDto = new AdminDto();
		adminDto.setName("Admin");
		adminDto.setPass("123");

		when(mapper.map(any(AdminDto.class), any())).thenReturn(admin);
		assertTrue(adminService.checkValidity(adminDto));
	}

}
