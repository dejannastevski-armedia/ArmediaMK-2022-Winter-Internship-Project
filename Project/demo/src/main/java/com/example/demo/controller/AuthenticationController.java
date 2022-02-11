package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserValidationException;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private QuestionService questionService;

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

    @PostMapping("/register/redirect-to-login")
    public String loginForm(User user, Model model)
    {
        String res = authenticationService.validateUser(user);
        if (res.length() == 0)
        {
            authenticationService.saveUser(user);
            return "login_user";

        }
        else
        {
            model.addAttribute("errorMessage", res);
            return "signup_form";
        }
    }

    @GetMapping("/login-user")
    public String login(Model model)
    {
        model.addAttribute("user", new User());

        return "login_user";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> homePage(@RequestBody UserDTO userDTO, Model model)
            throws UserValidationException
    {
        User u = authenticationService.validateUserForLogin(userDTO);
        return ResponseEntity.ok(u);

    }

    @GetMapping("/home")
    public String listQuestion(Model model)
    {

        List<Question> listQuestion = questionService.listAll();
        model.addAttribute("listQuestion", listQuestion);
        return "home";
    }

    @GetMapping("/admin")
    public String findAllByRoleUser(Model model)
    {
        List<User> listUsersWithRoleUser = authenticationService.findAllByRoleUser();
        model.addAttribute("listUsersWithRoleUser", listUsersWithRoleUser);
        return "admin";
    }

}
