package com.example.firstproject.services;

import com.example.firstproject.model.User;

public interface AuthenticationService
{
    boolean checkEmail(String email);
    boolean checkUserName(String userName);
    boolean checkPassword(String password);
    String validateUserRegistration(User user);
    void saveUser(User user);
    User createUser(User user);
    String loginUser(String email, String password);
}
