package com.epam.ui.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.entity.User;
import com.epam.service.user.UserSignUpService;
import com.epam.util.CorrectInput;

@Component
public class UserSignUpServiceUi {
    public static final Logger LOGGER = LogManager.getLogger(UserSignUpServiceUi.class);

    @Autowired
    UserSignUpService userSignUpService;
    
    
    @Autowired
    UserDashBoardUi userDashBoardUi;
    
    @Autowired
    User user;
    public User getNewUserInput() {
    	
    	
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
        
        boolean isMember = userSignUpService.checkIfAlreadyMember(newUser);
        if (isMember) {
            LOGGER.info("* User with same email is already a member, try signing");
            App.startApplication();
        } else {
            // add the user
            userSignUpService.addNewUser(newUser);
            UserSession.setLoggedUser(newUser);
            userDashBoardUi.assignTasks();
        }
    }

}
