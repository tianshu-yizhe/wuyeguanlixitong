package com.property.service.impl;

import com.property.model.User;
import com.property.repository.UserRepository;
import com.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getResidents() {
        return userRepository.findByRole("RESIDENT");
    }

    @Override
    public List<User> getTechnicians() {
        return userRepository.findByRole("TECHNICIAN");
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == null) {
            // New user - encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // Existing user - check if password was changed
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null && !user.getPassword().equals(existingUser.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}