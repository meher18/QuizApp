package com.epam.dto;

import javax.validation.constraints.NotEmpty;

public class AdminDto {

	private int id;

	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@NotEmpty(message = "Password cannot be empty")
	private String pass;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
