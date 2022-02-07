package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController
{
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addQuestions(@RequestBody QuestionDTO questionDTO)
    {
        String result = questionService.validateQuestionAndTitle(questionDTO);
        if (result.length() == 0)
        {
            questionService.createQuestion(questionDTO);
            return ResponseEntity.ok().body("result");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @RequestMapping(value = "/delete-question", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteQuestion(@RequestBody QuestionDTO questionDTO)
    {
        String result = questionService.deleteQuestion(questionDTO);
        if (result.length() == 0)
        {
            return ResponseEntity.ok().body("success");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

    }
}
