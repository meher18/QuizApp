package com.epam.data.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.data.repository.UserDao;
import com.epam.entity.User;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserLibrary {

    @Autowired
    UserDao userDao;

    public boolean addUser(User user) {
        userDao.addUser(user);
        return true;
    }

    public boolean editUser(int userId, User user) {
        userDao.editUser(userId, user);
        return true;
    }

    public boolean deleteUser(int userId) {
        userDao.deleteUser(userId);
        return true;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
