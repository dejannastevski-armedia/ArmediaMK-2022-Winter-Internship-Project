package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.UserValidationException;

import java.util.List;

public interface AuthenticationService
{
    // Create Read Update Delete
    boolean checkUserName(String firstName);

    boolean checkPassword(String password);

    boolean validateAndSave(User user);

    void save(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void update(Long id, User user);

    void deleteUserById(Long id);

    User login(String email, String password) throws UserValidationException;

    List<String> register(String email, String password, String userName, Integer age, String role);

    boolean checkEmail(String email);
}