package com.epam.exceptions;

public class InValidQuizId extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidQuizId(String str)
    {
        super(str);
    }
}