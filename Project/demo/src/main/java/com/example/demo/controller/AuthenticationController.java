package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/register/redirect-to-login")
    public String loginForm(User user, Model model) {
        String res = authenticationService.validateUser(user);
        if (res.length() == 0) {
            authenticationService.saveUser(user);
            return "login_user";

        } else {
            model.addAttribute("errorMessage", res);
            return "signup_form";
        }
    }

    @GetMapping("/login-user")
    public String login(Model model) {
        model.addAttribute("user", new User());

        return "login_user";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> homePage(@RequestBody UserDTO userDTO) {
        String result = authenticationService.validateUserForLogin(userDTO);
        if (result.length() == 0) {
            return ResponseEntity.ok().body("result");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
