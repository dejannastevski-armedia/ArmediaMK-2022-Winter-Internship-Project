package com.example.firstproject.controller;

import com.example.firstproject.dto.AnswerDTO;
import com.example.firstproject.model.Answer;
import com.example.firstproject.services.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/answer")
public class AnswerController
{
    @Autowired
    private AnswerService answerService;

    @GetMapping("/view-answer/{id}")
    public String viewAnswer(@PathVariable Long id, Model model)
    {
        model.addAttribute("questionId", id);
        return "viewAnswer";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveAnswer(@RequestBody AnswerDTO answerDTO)
    {
        String result = answerService.validateAnswer(answerDTO.getAnswer());
        if (result.length() == 0)
        {
            Answer answer = answerService.createAnswer(answerDTO.getAnswer(), answerDTO.getEmail(), answerDTO.getQuestionId());
            if (answer != null)
            {
                answerService.saveAnswer(answer);
            }
            else
            {
                result = "Invalid Question ID";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}