package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController
{

    @RequestMapping("/home")
    public String helloWorld(Model model)
    {
        model.addAttribute("user", new User());
        return "home";
    }

}
