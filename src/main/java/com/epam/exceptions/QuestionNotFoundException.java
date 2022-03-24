package com.epam.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionNotFoundException(String str)
    {
        super(str);
    }
}
