
package com.epam.ui.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.global.CorrectInput;

@Component
public class UserRedirectUi {


    public static final Logger LOGGER = LogManager.getLogger(UserRedirectUi.class);

    private UserRedirectUi()
    {}
    public static void redirect() {
        LOGGER.info("press 1 > USER DASHBOARD");
        int choice = CorrectInput.getInteger();
        if(choice == 1) {
            UserDashBoardUi userDashBoardUi = new UserDashBoardUi();
            userDashBoardUi.assignTasks();
        }else{
            UserRedirectUi.redirect();
        }
    }
}
