package com.example.demo.controller;

import antlr.StringUtils;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public String viewHomePage(Model model)
    {
        model.addAttribute("user",new User());
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("user",new User());

        return "signup_form";
    }
    @GetMapping("/home")
    public String showHomePage(Model model)
    {
        model.addAttribute("user", new User());

        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("user",new  User());
        return "login";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> logIn (String email, String password){
        List<String> res=authenticationService.login(email,password);
        if(res.size()==0){
            return ResponseEntity.ok("success");
        }
        else{
            return ResponseEntity.badRequest().body(String.join(", ",res));
        }
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestParam String email,
//                            @RequestParam String password,
//                            Model model)
//    {
//        ArrayList<String>list= (ArrayList<String>) authenticationService.login(email,password);
//        if(list.isEmpty()){
//            return "redirect:/home";
//        }
//        else {
//            model.addAttribute("error", list);
//            model.addAttribute("user", new User());
//            return "login";
//        }
//    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email ,
                               @RequestParam String password,
                               @RequestParam String userName,
                               @RequestParam(defaultValue = "21") Integer age ,
                               Model model)
    {
        ArrayList<String>res= (ArrayList<String>) authenticationService.register(email,password,userName,age);
        if(res.isEmpty())
        {
            return "redirect:/auth/login";
        }
        else
        {
            model.addAttribute("error",res);
            model.addAttribute("user", new User());
            return "signup_form";
        }
    }
}