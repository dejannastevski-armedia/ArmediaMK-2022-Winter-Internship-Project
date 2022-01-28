package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnswerController
{
    @GetMapping("/view-answer/{id}")
    public String viewAnswer(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("questionId", id);
        return "view_answer";
    }
}
