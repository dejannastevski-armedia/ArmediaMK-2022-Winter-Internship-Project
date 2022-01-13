package com.example.firstproject.controller;

import com.example.firstproject.models.User;
import com.example.firstproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage()
    {
        return "blank_page";
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/redirect-to-login")
    public String processRegister(User user)
    {
        userRepository.save(user);

        return "login";
    }
}
