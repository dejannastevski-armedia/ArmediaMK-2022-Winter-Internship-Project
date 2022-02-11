package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserValidationException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private UserRepository useRepo;

    @Override
    public String validateUser(User user)
    {
        String res = "";
        if (!validateEmail(user.getEmail()))
        {
            res += "Invalid email,";
        }
        if (!validatePassword(user.getPassword()))
        {
            res += "Invalid password,";
        }
        else
        {
            User newUser = useRepo.findByEmail(user.getEmail());
            if (newUser != null)
            {
                res += "Email already exist";
            }
        }
        return res;
    }

    @Override
    public boolean validatePassword(String password)
    {
        boolean isValid = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if ((password.length() < 7) || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars)
                || !password.matches(specialChars))
        {
            isValid = false;
        }
        return isValid;

    }

    @Override
    public boolean validateEmail(String email)
    {
        String regex = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateUsername(String username)
    {
        if (username == null || username.length() <= 2 || username.isEmpty())
        {
            return false;
        }

        return true;

    }

    @Override
    public void saveUser(User user)
    {
        encryptPassword(user);
        useRepo.save(user);
    }

    @Override
    public User validateUserForLogin(UserDTO userDTO) throws UserValidationException
    {

        if (userDTO.getEmail() == null || !validateEmail(userDTO.getEmail()))
        {
            throw new UserValidationException(" Invalid email, ");
        }
        if (userDTO.getPassword() == null || !validatePassword(userDTO.getPassword()))
        {
            throw new UserValidationException(" Invalid password ");

        }

        User existing = useRepo.findByEmail(userDTO.getEmail());
        if (existing == null)
        {
            throw new UserValidationException(" Email not exist ");

        }
        else
        {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(userDTO.getPassword(), existing.getPassword()))
            {
                throw new UserValidationException(" Incorrect password ");

            }
        }
        return existing;

    }

    @Override
    public List<User> findAllByRoleUser()
    {
        return useRepo.findAllByRoleUser();

    }

    private void encryptPassword(User user)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

}
