package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserValidationException;
import com.example.demo.model.User;

import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService
{

    boolean validateEmail(String email);

    boolean validatePassword(String password);

    String validateUser(User user);

    boolean validateUsername(String username);

    void saveUser(User user);

    User validateUserForLogin(UserDTO userDTO) throws UserValidationException;

}
