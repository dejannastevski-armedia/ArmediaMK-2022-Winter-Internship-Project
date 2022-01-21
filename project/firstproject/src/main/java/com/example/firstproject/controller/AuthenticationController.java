package com.example.firstproject.controller;

import com.example.firstproject.dto.UserLoginDTO;
import com.example.firstproject.model.User;
import com.example.firstproject.services.AuthenticationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if(result.length() == 0)
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
    public ResponseEntity<String> loginUser(@RequestBody @NotNull UserLoginDTO userdto)
    {
        String result = authenticationService.loginUser(userdto.getEmail(), userdto.getPassword());
        if(result.length() == 0)
        {
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/logged-in")
    public String loggedIn()
    {
        return "loggedIn";
    }
}