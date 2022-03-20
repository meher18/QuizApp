package com.epam.exceptions.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.epam.exceptions.QuizException;

@ControllerAdvice
public class QuizExceptionHandler {

	@ExceptionHandler(QuizException.class)
	public Model handleException(QuizException exception) {
		return exception.getModel();
	}
}
