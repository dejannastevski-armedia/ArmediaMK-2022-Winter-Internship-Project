package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository useRepo;

    @Override
    public String validateUser(User user) {
        String res = "";
        if (!validateEmail(user.getEmail())) {
            res += " Invalid email,  ";
        }
        if (!validatePassword(user.getPassword())) {
            res += " Invalid password,  ";
        } else {
            User newUser = useRepo.findByEmail(user.getEmail());
            if (newUser != null) {
                res += " Email already exist ";
            }
        }
        return res;
    }


    @Override
    public boolean validatePassword(String password) {
        boolean isValid = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if ((password.length() < 7) || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(specialChars)) {
            isValid = false;
        }
        return isValid;

    }


    @Override
    public boolean validateEmail(String email) {
        String regex = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateUsername(String username) {
        if (username == null || username.length() <= 2 || username.isEmpty()) {
            return false;
        }

        return true;

    }

    @Override
    public void saveUser(User user) {
        encryptPassword(user);
        useRepo.save(user);
    }

    @Override
    public String validateUserForLogin(UserDTO user) {
        String result = "";
        if (user.getEmail() == null || !validateEmail(user.getEmail())) {
            result += " Invalid email, ";
        }
        if (user.getPassword() == null || !validatePassword(user.getPassword())) {
            result += " Invalid password ";
        }
        if (result.length() == 0) {
            User existing = useRepo.findByEmail(user.getEmail());
            if (existing == null) {
                result += " Email not exist ";
            } else {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (!passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
                    result += " Incorrect password ";
                }
            }

        }
        return result;

    }


    private void encryptPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


}
