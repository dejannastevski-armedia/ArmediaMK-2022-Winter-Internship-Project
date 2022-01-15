package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

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
    public String getLoginPage(Model model){
        model.addAttribute("user",new  User());
        return "login";
    }

    @PostMapping("/login")
    public String processRegister(User user, Model model)
    {
        model.addAttribute("user", new User());
        authenticationService.save(user);

        return "login";
    }

////    @GetMapping("/index")
////    public String getIndexPage(Model model)
////    {
////        model.addAttribute("user", new User());
////        return "index";
////    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        this.authenticationService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model)
    {
        List<User>listUsers=authenticationService.getAllUsers();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

}