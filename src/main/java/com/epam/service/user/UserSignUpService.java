package com.epam.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.entity.User;
import com.epam.service.libraryservice.UserLibraryService;

@Component
public class UserSignUpService {

    @Autowired
    UserLibraryService userLibrary;

    public boolean checkIfAlreadyMember(User newUser)
    {
        boolean isAlreadyMember = false;
        if (!userLibrary.getAllUsers().isEmpty()) {
            for (User user : userLibrary.getAllUsers()) {
                if (user.getUserName().equals(newUser.getUserName())) {
                    isAlreadyMember = true;
                    break;
                }
            }
        }
        return isAlreadyMember;
    }
    public void addNewUser(User newUser) {
       userLibrary.addUser(newUser);
    }
}
