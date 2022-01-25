package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping("/")
    public String helloWorld(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

}
