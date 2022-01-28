package com.example.demo.controller;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/answer")
public class AnswerController
{

    @Autowired
    AnswerService answerService;

    @GetMapping("/answer/{id}")
    public String AnswerPage(@PathVariable Long id, Model model)
    {
        model.addAttribute("id", id);
        return "answer";
    }

    @RequestMapping(path = "/add-answer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addAnswer(@RequestBody AnswerDTO answerDTO)
    {
        String res = String.valueOf(answerService.createAnswer(answerDTO));

        if (res == null || res == "[]")
        {
            return ResponseEntity.ok("success");
        }
        else
        {
            return ResponseEntity.badRequest().body(res);
        }
    }
}
