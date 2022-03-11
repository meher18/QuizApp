package com.epam.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.epam.config.AppContext;
import com.epam.ui.admin.AdminServiceUi;
import com.epam.ui.user.UserServiceUi;
import com.epam.util.CorrectInput;

@Component
public class AppUi {

    public static final Logger LOGGER = LogManager.getLogger(AppUi.class);

    public static int getInputForStartingApp()
    {
        String horizontalRule = "-".repeat(23);
        LOGGER.info(horizontalRule);
        LOGGER.info("* Welcome to quiz app *");
        LOGGER.info(horizontalRule);
        LOGGER.info("- Choose below operations");
        LOGGER.info("Press 1 > ADMIN");
        LOGGER.info("Press 2 > USER");
        LOGGER.info("Press 3 > TO CLOSE APP");
        return CorrectInput.getInteger();
    }

    public void createApp()
    {
        int choice = AppUi.getInputForStartingApp();
        if (choice == 1) {
            AdminServiceUi adminServiceUi = AppContext.getApplicationContext().getBean(AdminServiceUi.class);
            adminServiceUi.checkAdmin();
        } else if (choice == 2) {
            UserServiceUi userServiceUi = AppContext.getApplicationContext().getBean(UserServiceUi.class);
            userServiceUi.startUserService();
        } else if (choice == 3) {
            LOGGER.info("Bye...");
        }
    }
}
