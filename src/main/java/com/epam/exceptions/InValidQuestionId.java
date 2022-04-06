package com.epam.exceptions;

public class InValidQuestionId extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidQuestionId(String str)
    {
        super(str);
    }
}