package com.example.demo.controller;

import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    QuestionService questionService;

    @RequestMapping("/")
    public String helloWorld(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/home")
    public String listQuestions(Model model)
    {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "home";
    }

}
