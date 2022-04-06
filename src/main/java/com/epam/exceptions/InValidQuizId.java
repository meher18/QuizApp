package com.epam.exceptions;

public class InValidQuizId extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidQuizId(String str)
    {
        super(str);
    }
}