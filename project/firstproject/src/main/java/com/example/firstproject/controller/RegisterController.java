package com.example.firstproject.controller;

import com.example.firstproject.models.User;
import com.example.firstproject.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class RegisterController
{
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/register/register-page")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/register/redirect-to-login")
    public String loginForm(User user, Model model)
    {
        String result = authenticationService.validate(user);
        if(result.length() == 0)
        {
            User toEnter = authenticationService.encryptPassword(user);
            authenticationService.saveUser(toEnter);
            return "login";
        }
        else
        {
            model.addAttribute("errorMessage", result);
            return "index";
        }
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}