package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answer")
public class AnswerController
{
    @GetMapping("/answer/{id}")
    public String AnswerPage(@PathVariable long id)
    {
        return "answer";
    }
}
