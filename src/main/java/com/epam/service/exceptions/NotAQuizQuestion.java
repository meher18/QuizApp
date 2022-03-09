package com.epam.service.exceptions;

public class NotAQuizQuestion extends Exception{
    public NotAQuizQuestion(String str)
    {
        super(str);
    }
}