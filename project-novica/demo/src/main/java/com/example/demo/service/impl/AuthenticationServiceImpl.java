package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.InvalidargumentException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public boolean checkUserName(String firstName) {
        if(firstName.length() >=3){
            return true;
        }
        return false;
    }
    @Override
    public boolean checkLastName(String lastName) {
        if(lastName.length()>=5){
            return true;
        }
        return false;
    }
    @Override
    public boolean checkPassword(String password) {
        boolean isValid=true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if(!(password.length()>=7) || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(specialChars)){
            isValid=false;
        }
        return isValid;
    }
    @Override
    public boolean validateAndSave(User user) {
        if(checkUserName(user.getFirstName()) && checkLastName(user.getLastName()) && checkPassword(user.getPassword())){
            userRepository.save(user);
            return true;
        }
        return false;
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public void update(Long id, User user) {

    }

//    @Override
//    public void update(Long id, User user) {
//    User user1=this.getUserById(id);
//    user1.setEmail(user.getEmail());
//    user1.setFirstName(user.getFirstName());
//    user1.setLastName(user.getLastName());
//    user1.setPassword(user.getPassword());
//
//    this.save(user1);
//    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User login(String email, String password) throws InvalidargumentException {
        if(email==null || email.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidargumentException();
        }
        return null;
    }

    @Override
    public User register(String email, String password, String firstName, String lastName) throws InvalidargumentException {
        if(email==null || email.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidargumentException();
        }
        User user = new User(email,password);
        return userRepository.save(user);
    }
    //    @Override
//    public boolean checkEmail(String email) {
//        boolean isValid=true;
//        try{
//            InternetAddress emailAddr = new InternetAddress(email);
//
//        } catch (AddressException e) {
//            isValid=false;
//        }
//        return isValid;
//    }
}