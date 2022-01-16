package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.EmailAlreadyExistException;
import com.example.demo.model.exceptions.InvalidargumentException;


import java.util.List;

public interface AuthenticationService {
    //Create Read Update Delete
    boolean checkUserName(String firstName);

    boolean checkLastName(String lastName);

    boolean checkPassword(String password);

  //  boolean checkEmail(String email);

    boolean validateAndSave(User user);

    void save(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void update(Long id, User user);

    void deleteUserById(Long id);

    User login(String email, String password) throws InvalidargumentException;

    User register(String email, String password, String firstName, String lastName) throws InvalidargumentException, EmailAlreadyExistException;
}

