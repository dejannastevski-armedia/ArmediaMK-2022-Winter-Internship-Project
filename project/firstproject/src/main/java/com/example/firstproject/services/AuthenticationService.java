package com.example.firstproject.services;

import com.example.firstproject.models.User;

public interface AuthenticationService
{
    boolean checkEmail(String email);
    boolean checkUserName(String userName);
    boolean checkPassword(String password);
    String validate(User user);
    void saveUser(User user);
}
