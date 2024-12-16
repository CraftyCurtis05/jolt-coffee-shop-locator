package com.jolt.dao;

import com.jolt.model.RegisterUserDto;
import com.jolt.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}