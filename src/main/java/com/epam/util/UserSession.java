
package com.epam.util;

import org.springframework.stereotype.Component;

import com.epam.entity.User;

@Component
public class UserSession {
    private UserSession()
    {}
    private static User loggedUser = null;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        UserSession.loggedUser = loggedUser;
    }
}
