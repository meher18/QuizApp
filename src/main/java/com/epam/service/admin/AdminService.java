package com.epam.service.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.AdminDto;
import com.epam.entity.Admin;

@Component
public class AdminService {

	private static final String ADMIN_NAME = "Admin";
	private static final String ADMIN_PASSWORD = "123";

	@Autowired
	ModelMapper mapper;

	public boolean checkValidity(AdminDto adminDto) {

		Admin currentAdmin = mapper.map(adminDto, Admin.class);

		return currentAdmin.getName().equals(ADMIN_NAME)
				&& currentAdmin.getPass().equals(ADMIN_PASSWORD);
	}
}
