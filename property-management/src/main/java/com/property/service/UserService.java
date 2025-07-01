package com.property.service;

import com.property.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getResidents();
    List<User> getTechnicians();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User saveUser(User user);
    void deleteUser(Long id);
}