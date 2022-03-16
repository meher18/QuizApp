package com.epam.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.User;
import com.epam.service.libraryservice.UserLibraryService;

@Component
public class UserSignInService {

    @Autowired
    UserLibraryService userLibrary;
    
    
    public User validate( User currentUser) {
        
    	User validUser = null;

        if (!userLibrary.getAllUsers().isEmpty()) {
            for (User userFromDb : userLibrary.getAllUsers()) {
                if (userFromDb.getUserName().equals(currentUser.getUserName())
                        && userFromDb.getUserPassword().equals(currentUser.getUserPassword())) {
                    validUser = userFromDb;
                    break;
                }
            }
        }
        return validUser;
    }

}
