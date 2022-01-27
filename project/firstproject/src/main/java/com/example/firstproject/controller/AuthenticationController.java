package com.example.firstproject.controller;

import com.example.firstproject.dto.UserLoginDTO;
import com.example.firstproject.exceptions.UserValidationException;
import com.example.firstproject.model.Question;
import com.example.firstproject.model.User;
import com.example.firstproject.services.AuthenticationService;

import org.jetbrains.annotations.NotNull;
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
@RequestMapping(value = "/auth")
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/register/register-page")
    public String registerPage(Model model)
    {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/register/redirect-to-login")
    public String loginForm(User user, Model model)
    {
        String result = authenticationService.validateUserRegistration(user);
        if (result.length() == 0)
        {
            User toEnter = authenticationService.createUser(user);
            authenticationService.saveUser(toEnter);
            return "login";
        }
        else
        {
            model.addAttribute("errorMessage", result);
            return "index";
        }
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> loginUser(@RequestBody @NotNull UserLoginDTO userDTO) throws UserValidationException
    {
        User user = authenticationService.loginUser(userDTO.getEmail(), userDTO.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logged-in")
    public String loggedIn(Model model)
    {
        List<Question> questionList = authenticationService.listAllQuestions();
        model.addAttribute("listQuestions", questionList);
        return "loggedIn";
    }
}