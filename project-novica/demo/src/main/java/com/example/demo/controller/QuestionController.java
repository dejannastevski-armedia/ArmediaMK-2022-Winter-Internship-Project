package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/question")
public class QuestionController
{
    @Autowired
    QuestionService questionService;

    @RequestMapping(path = "/add-question", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDTO questionDTO)
    {
        String res = questionService.createQuestion(questionDTO);

        if (res == null || res.isEmpty())
        {
            return ResponseEntity.ok("success");
        }
        else
        {
            return ResponseEntity.badRequest().body(res);
        }
    }

    @RequestMapping(path = "/delete-question", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteQuestion(@RequestBody QuestionDTO questionDTO) throws IllegalAccessException
    {
        questionService.deleteQuestion(questionDTO);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(path = "/edit-question", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> editQuestion(@RequestBody QuestionDTO questionDTO)
    {
        String res = questionService.editQuestion(questionDTO);
        if (res == null || res.isEmpty())
        {
            return ResponseEntity.ok("success");
        }
        else
        {
            return ResponseEntity.badRequest().body(res);
        }
    }
}