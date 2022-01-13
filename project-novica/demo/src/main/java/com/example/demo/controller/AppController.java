package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AppController
{

    @Autowired
    private UserRepository userRepository;

    public AppController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


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



   @PostMapping("/login")
    public String processRegister(User user, Model model)
    {
        model.addAttribute("user", new User());
        userRepository.save(user);

        return "index";
    }
    @GetMapping("/index")
    public String getIndexPage(Model model)
    {
        model.addAttribute("user", new User());
        return "index";
    }
    @GetMapping("/users")
    public String listUsers(Model model)
    {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
}
