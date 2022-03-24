package com.epam.util;


import org.springframework.stereotype.Component;

@Component
public class Constants {
    private Constants()
    {

    }
  
    public static final String INVALID_INPUT = "Invalid Input, Please enter only digits";
    public static final String QUIZ_HOSTED = "HOSTED";
    public static final String QUIZ_NOT_HOSTED = "NOT HOSTED";
    public static final String INVALID_QUESTION_ID = "Invalid question Id, Please enter correct id";
    public static final String INVALID_QUESTION_DELETION = "This question is part of some quiz," +
            " please delete or modify the quiz before deleting question";
    public static final String INVALID_QUIZ_ID = "InValid quiz id, please enter a valid one";
    public static final String INVALID_CREDENTIALS = "Invalid Credentials, please check again";
    public static final String LOGIN_SUCCESS = "Login Success, WELCOME !";
    public static final String LOGIN_FAILED = "Login Failed, please check again!";

    public static final String SESSION_ADMIN_USER_NAME = "adminUserName";
    public static final String SESSION_USER_NAME = "userName";
    public static final String SESSION_USER_EMAIL = "userEmail";
}
