package com.example.firstproject.services;

import com.example.firstproject.model.User;
import com.example.firstproject.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkEmail(String email)
    {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserName(String userName)
    {
        if(userName == null || userName.isEmpty() || userName.length() <= 2)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String password)
    {
        String regexPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,}";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password)
    {
        StringBuilder sb = new StringBuilder();
        if(email ==  null || !checkEmail(email))
        {
            sb.append("Invalid E-Mail!\n");
        }
        if(password == null || !checkPassword(password))
        {
            sb.append("Password Validation Error!\n");
        }
        if(sb.length() == 0)
        {
            User dbUser = userRepository.findByEmail(email);
            if(dbUser == null)
            {
                sb.append("User Not Found!\n");
            }
                else
            {
                if(!passwordEncoder.matches(password,dbUser.getPassword()))
                {
                    sb.append("Invalid Password!\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public User createUser(User user)
    {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setAge(user.getAge());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return newUser;
    }

    @Override
    public String validateUserRegistration(@NotNull User user)
    {
        StringBuilder sb = new StringBuilder();
        if (!checkUserName(user.getUserName())) {
            sb.append(" Username Not Valid!");
            sb.append("\n");
        }
        if (!checkPassword(user.getPassword())) {
            sb.append(" Password Not Valid! \n");
        }
        if (!checkEmail(user.getEmail())) {
            sb.append(" Email Not Valid!\n");
        } else {
            User newUser = userRepository.findByEmail(user.getEmail());
            if (newUser != null) {
                sb.append("Email Already Exist!\n");
            }
        }
        return sb.toString();
    }
}
