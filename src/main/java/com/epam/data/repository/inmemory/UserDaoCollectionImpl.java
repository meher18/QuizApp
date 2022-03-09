package com.epam.data.repository.inmemory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.data.repository.UserDao;
import com.epam.entity.User;

@Component
public class UserDaoCollectionImpl implements UserDao {

    @Autowired
    List<User> users;

    public UserDaoCollectionImpl() {
        users = new ArrayList<>();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void editUser(int userId, User user) {
        users.set(userId, user);
    }

    @Override
    public void deleteUser(int userId) {
        users.remove(userId);
    }
}
