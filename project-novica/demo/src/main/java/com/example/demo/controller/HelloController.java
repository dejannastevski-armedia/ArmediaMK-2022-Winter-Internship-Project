package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping("/home")
    public String helloWorld(Model model)
    {
        model.addAttribute("user", new User());
        return "home";
    }

}
