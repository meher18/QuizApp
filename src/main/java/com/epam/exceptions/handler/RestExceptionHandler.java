package com.epam.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.exceptions.InValidQuestionId;
import com.epam.exceptions.QuizzesNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

//	@ResponseBody
	@ExceptionHandler(QuizzesNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ExceptionResponse> handleException(QuizzesNotFoundException exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.NO_CONTENT.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
	}
	
	
	@ExceptionHandler(InValidQuestionId.class)
	public ResponseEntity<ExceptionResponse> invalidQuestionIdException(InValidQuestionId exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleGlobalException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
