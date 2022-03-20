package com.epam.exceptions;

public class InValidQuestionId extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidQuestionId(String str)
    {
        super(str);
    }
}