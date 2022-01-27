package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/answer")
public class AnswerController
{
    @GetMapping("/view-answer")
    public String viewAnswer()
    {
        return "viewAnswer";
    }
}