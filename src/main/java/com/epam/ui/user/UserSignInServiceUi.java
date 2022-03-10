package com.epam.ui.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.App;
import com.epam.entity.User;
import com.epam.global.CorrectInput;
import com.epam.service.user.UserSignInService;

@Component
public class UserSignInServiceUi {

	public static final Logger LOGGER = LogManager.getLogger(UserSignInServiceUi.class);

	@Autowired
	UserSignInService userSignInService;
	
	@Autowired
	UserDashBoardUi userDashBoardUi;

	@Autowired
	User user ;
	
	public User getUserInput() {
		
		
		LOGGER.info("Enter User Name: ");
		String name = CorrectInput.getString();
		LOGGER.info("Enter Password: ");
		String password = CorrectInput.getString();
		
		user.setUserName(name);
		user.setUserPassword(password);
		
		return user;
	}

	public void signIn() {
		
		User user = getUserInput();
		User validUser = userSignInService.validate(user);
		
		if (validUser != null) {
			// start session for user
			UserSession.setLoggedUser(validUser);
			userDashBoardUi.assignTasks();
		} else {
			LOGGER.warn("Invalid credentials");
			App.startApplication();
		}
	}
}
