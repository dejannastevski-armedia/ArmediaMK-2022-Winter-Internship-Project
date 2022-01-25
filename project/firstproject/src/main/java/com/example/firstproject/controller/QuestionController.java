package com.example.firstproject.controller;

import com.example.firstproject.dto.QuestionDTO;
import com.example.firstproject.model.Question;
import com.example.firstproject.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/question")
public class QuestionController
{
    @Autowired
    private QuestionService questionService;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveQuestion(@RequestBody QuestionDTO questionDTO)
    {
        String result = questionService.validateQuestion(questionDTO.getQuestion(), questionDTO.getTitle());
        if(result.length() == 0)
        {
            Question question = questionService.createQuestion(questionDTO.getQuestion(), questionDTO.getTitle());
            questionService.saveQuestion(question);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}
