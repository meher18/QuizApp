package com.epam.util;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;

@Component
public class CorrectInput {

    static Scanner sc = (Scanner) AppContext.getApplicationContext().getBean("scanner");

    public static final Logger LOGGER = LogManager.getLogger(CorrectInput.class);

    private CorrectInput()
    {}

    public static String getString()
    {
       return sc.nextLine();
    }

    public static int getInteger()
    {
        boolean isNotValid;
        int inputVal = 0;
        do  {
            try {
                inputVal = Integer.parseInt(sc.nextLine());
                isNotValid = false;
            } catch (NumberFormatException e) {
                isNotValid = true;
                LOGGER.warn(Constants.INVALID_INPUT);
            }
        }while (isNotValid);
        return inputVal;
    }
}
