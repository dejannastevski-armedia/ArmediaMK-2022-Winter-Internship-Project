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

@RestController
@RequestMapping(value = "/app")
public class AppController
{

    @Autowired
    private UserRepository userRepository;

    public AppController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @RequestMapping("/proba")
    public String returnHomeString(){
        return "Welcome";
    }


    @GetMapping("")
    public String viewHomePage()
    {
        return "index";
    }

    @PostMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user", new User());

        return "signup_form.html";
    }
//    @PostMapping("/process_register")
//    public String processRegister(User user)
//    {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        userRepository.save(user);
//
//        return "register_success";
//    }
    @GetMapping("/users")
    public String listUsers(Model model)
    {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
}
