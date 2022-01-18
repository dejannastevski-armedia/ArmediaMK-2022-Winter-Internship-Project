package com.example.demo.service;

import com.example.demo.model.User;


import java.util.List;
import java.util.Optional;

public interface AuthenticationService {
    //Create Read Update Delete
    boolean checkUserName(String firstName);

    boolean checkPassword(String password);

    boolean validateAndSave(User user);

    void save(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void update(Long id, User user);

    void deleteUserById(Long id);

    List<String> login(String email, String password);

    List<String> register(String email, String password, String userName, Integer age) ;

    boolean checkEmail(String email);
}