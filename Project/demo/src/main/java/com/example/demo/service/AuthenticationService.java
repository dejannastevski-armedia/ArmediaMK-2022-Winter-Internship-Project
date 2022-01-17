package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

boolean validateEmail(String email);
    boolean validatePassword(String password);
    String validateAndSave(User user);
    boolean validateUsername(String username);
    void saveValidUser(User user);
}
