package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnswerController
{
    @GetMapping("/answer")
    public String AnswerPage()
    {
        return "answer";
    }
}
