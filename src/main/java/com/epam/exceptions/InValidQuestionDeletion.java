package com.epam.exceptions;

public class InValidQuestionDeletion extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidQuestionDeletion(String str)
    {
        super(str);
    }
}