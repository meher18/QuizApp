package com.epam.exceptions;

public class QuizNotFound extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizNotFound(String str)
    {
        super(str);
    }
}