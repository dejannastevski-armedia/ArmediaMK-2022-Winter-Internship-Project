package com.example.demo.controller;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.service.AnswerService;

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

import java.util.List;

@Controller
public class AnswerController
{
    @Autowired
    private AnswerService answerService;

    @GetMapping("/view-answer/{id}")
    public String viewAnswer(@PathVariable("id") Long id, Model model)
    {
        List<Answer> answerList = answerService.listAllAnswers(id);
        model.addAttribute("answerList", answerList);
        model.addAttribute("questionId", id);
        return "view_answer";
    }

    @RequestMapping(value = "/add-answer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addAnswer(@RequestBody AnswerDTO answerDTO)
    {
        String result = answerService.validateAnswer(answerDTO);
        if (result.length() == 0)
        {
            Answer answer = answerService.createAnswer(answerDTO);
            if (answer != null)
            {
                return ResponseEntity.ok().body("result");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid question id");
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
