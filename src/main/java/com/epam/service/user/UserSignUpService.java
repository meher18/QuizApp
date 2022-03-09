package com.epam.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.library.UserLibrary;
import com.epam.entity.User;

@Component
public class UserSignUpService {

    @Autowired
    UserLibrary userLibrary;

    public boolean checkIfAlreadyMember(User newUser)
    {
        boolean isAlreadyMember = false;
        if (!userLibrary.getAllUsers().isEmpty()) {
            for (User user : userLibrary.getAllUsers()) {
                if (user.getEmail().equals(newUser.getEmail())) {
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
