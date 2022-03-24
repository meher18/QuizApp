package com.epam.exceptions;

import org.springframework.ui.Model;

public class QuizzesNotFoundException  extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizzesNotFoundException(){
		
	}
	
	public QuizzesNotFoundException(Model model, String errorMessage, String successMessage) {
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
