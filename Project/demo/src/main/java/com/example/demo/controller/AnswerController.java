package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnswerController
{

    @GetMapping("/view-answer/{id}")
    public String viewAnswer(@PathVariable("id") Long id)
    {
        return "view_answer";
    }
}
