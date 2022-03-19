package com.epam.exceptions;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuizException  extends Exception{
	
	public QuizException(){
		
	}
	
	public QuizException(Model model, String errorMessage, String successMessage) {
		super();
		this.model = model;
		this.errorMessage = errorMessage;
		this.successMessage = successMessage;
	}

	private Model model;
	private String errorMessage;
	
	private String successMessage;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	

}