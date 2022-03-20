package com.epam.exceptions;

public class NotAQuizQuestion extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAQuizQuestion(String str)
    {
        super(str);
    }
}