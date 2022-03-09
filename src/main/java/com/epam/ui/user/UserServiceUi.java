package com.epam.ui.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.config.AppContext;
import com.epam.global.CorrectInput;

@Component
public class UserServiceUi {


    public static final Logger LOGGER = LogManager.getLogger(UserServiceUi.class);

    public int getUserServiceInput() {
        String heading = "----".repeat(5) + "User" + "----".repeat(5);
        LOGGER.info(heading);
        LOGGER.info("Press 1 > TO SIGN IN");
        LOGGER.info("Press 2 > TO SIGN UP");
        LOGGER.info("Press 3 > TO EXIT");
        return CorrectInput.getInteger();
    }

    public void startUserService() {
        int choice = getUserServiceInput();

        if (choice == 1) {
            UserSignInServiceUi userSignInServiceUi = AppContext.getApplicationContext().getBean(UserSignInServiceUi.class);
            userSignInServiceUi.signIn();
        } else if (choice == 2) {
            UserSignUpServiceUi userSignUpServiceUi = AppContext.getApplicationContext().getBean(UserSignUpServiceUi.class);
            userSignUpServiceUi.signUp();
        } else {
            App.startApplication();
        }
    }
}
