package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.EmailAlreadyExistException;
import com.example.demo.model.exceptions.InvalidArgumentException;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.PasswordHashing;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/auth")
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordHashing passwordHashing;

    @GetMapping("")
    public String viewHomePage(Model model)
    {
        model.addAttribute("user",new  User());
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("user",new  User());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email ,
                               @RequestParam String password,
                               @RequestParam String userName,
                               @RequestParam(defaultValue = "21") Integer age ,
                               Model model)
    {
        ArrayList<String>res= (ArrayList<String>) authenticationService.register(email,password,userName,age);
        if(res.isEmpty())
        {
            return "redirect:/auth/login";
        }
        else
        {
            model.addAttribute("error",res);
            model.addAttribute("user", new User());
            return "signup_form";
        }
    }
}