package com.epam.exceptions;

public class InValidQuestionDeletion extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidQuestionDeletion(String str)
    {
        super(str);
    }
}