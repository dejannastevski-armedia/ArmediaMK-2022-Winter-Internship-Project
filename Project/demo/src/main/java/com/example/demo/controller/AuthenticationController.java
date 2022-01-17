package com.example.demo.controller;

import com.example.demo.service.AuthenticationService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage()
    {
        return "index";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/register/redirect-to-login")
    public String loginForm(User user, Model model)
    {
        String res = authenticationService.validateAndSave(user);
        if(res.length() == 0)
        {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            authenticationService.saveValidUser(user);
            return "login_user";

        }
        else
        {
            model.addAttribute("errorMessage", res);
            return "signup_form";
        }
    }
    @GetMapping("/login-user")
    public String login(Model model)
    {
        model.addAttribute("user", new User());

        return "login_user";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {


        return "users";
    }



}
