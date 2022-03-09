package com.epam.ui.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.config.AppContext;
import com.epam.entity.User;
import com.epam.global.CorrectInput;
import com.epam.service.user.UserSignUpService;

@Component
public class UserSignUpServiceUi {
    public static final Logger LOGGER = LogManager.getLogger(UserSignUpServiceUi.class);

    public User getNewUserInput() {
        User user = new User();
        LOGGER.info("Enter User Name: ");
        String name = CorrectInput.getString();
        LOGGER.info("Enter Email: ");
        String email = CorrectInput.getString();
        LOGGER.info("Enter Password: ");
        String password = CorrectInput.getString();
        user.setUserName(name);
        user.setEmail(email);
        user.setUserPassword(password);

        return user;
    }

    public void signUp() {
        User newUser = getNewUserInput();
        UserSignUpService userSignUpService = AppContext.getApplicationContext().getBean(UserSignUpService.class);

        boolean isMember = userSignUpService.checkIfAlreadyMember(newUser);
        if (isMember) {
            LOGGER.info("* User with same email is already a member, try signing");
            App.startApplication();
        } else {
            // add the user
            userSignUpService.addNewUser(newUser);
            UserSession.setLoggedUser(newUser);
            UserDashBoardUi userDashBoardUi = AppContext.getApplicationContext().getBean(UserDashBoardUi.class);
            userDashBoardUi.assignTasks();
        }
    }

}
