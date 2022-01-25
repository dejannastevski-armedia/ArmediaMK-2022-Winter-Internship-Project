package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
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
            @RequestParam(defaultValue = "21") Integer age,
            Model model)
    {
        ArrayList<String> res = (ArrayList<String>) authenticationService.register(email, password, userName, age);
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
    public ResponseEntity<String> logIn(@RequestBody UserDTO userDTO)
    {
        List<String> res = authenticationService.login(userDTO.getEmail(), userDTO.getPassword());
        if (res.size() == 0)
        {
            return ResponseEntity.ok("success");
        }
        else
        {
            return ResponseEntity.badRequest().body(String.join(", ", res));
        }
    }



}