package com.epam.data.dao;

import java.util.List;

import com.epam.entity.User;

public interface UserDao {
	
    List<User> getAllUsers();

    void addUser(User user);

    void editUser(int userId, User user);

    void deleteUser(int id);
}
