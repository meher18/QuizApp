package com.epam.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {

	private int id;

	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@NotEmpty(message = "Password cannot be empty")
	private String pass;

}
