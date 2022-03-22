package com.epam.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.exceptions.QuizException;

@ControllerAdvice
public class QuizExceptionHandler {

	@ResponseBody
	@ExceptionHandler(QuizException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleException(QuizException exception) {
		return exception.getErrorMessage();
	}
}
