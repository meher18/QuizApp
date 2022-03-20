package com.epam.exceptions;

public class InValidQuestion extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InValidQuestion(String str)
    {
        super(str);
    }
}
