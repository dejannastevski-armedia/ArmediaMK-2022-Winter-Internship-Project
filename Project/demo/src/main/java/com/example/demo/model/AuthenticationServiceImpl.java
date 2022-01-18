package com.example.demo.model;

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
            res += " Invalid email \n ";
        }
        if (!validatePassword(user.getPassword())) {
            res += " Invalid password \n ";
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
        if (username.length() <= 2 || username.isEmpty() || username == null) {
            return false;
        }

        return true;

    }

    @Override
    public void saveUser(User user) {
        encryptPassword(user);
        useRepo.save(user);
    }

    private void encryptPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


}
