package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.UserValidationException;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.QuestionService;
import com.example.demo.util.PasswordHashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("")
    public String viewHomePage(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user", new User());
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "signup_form";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
            @RequestParam String password,
            @RequestParam String userName,
            @RequestParam Integer age,
            @RequestParam String role,
            Model model)
    {
        ArrayList<String> res = (ArrayList<String>) authenticationService.register(email, password, userName, age, role);
        if (res.isEmpty())
        {
            return "redirect:/auth/login";
        }
        else
        {
            model.addAttribute("error", res);
            model.addAttribute("user", new User());
            return "signup_form";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> logIn(@RequestBody UserDTO userDTO) throws UserValidationException
    {
        User res = authenticationService.login(userDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/admin")
    public String listUserWithRoleUser(Model model)
    {
        List<User> listUserWithRoleUser = authenticationService.listAllUsersWithRoleUser();
        model.addAttribute("listUserWithRoleUser", listUserWithRoleUser);
        return "admin";
    }
}