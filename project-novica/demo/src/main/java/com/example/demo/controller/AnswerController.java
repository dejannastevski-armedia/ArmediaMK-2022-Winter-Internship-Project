package com.example.demo.controller;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.UserAnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/answer")
public class AnswerController
{

    @Autowired
    AnswerService answerService;

    @GetMapping("/answer/{id}")
    public String AnswerPage(@PathVariable Long id, Model model)
    {
        List<Answer> answers = answerService.listAllAnswersPerQuestion(id);
        model.addAttribute("answers", answers);
        return "answer";
    }

    @RequestMapping(path = "/add-answer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addAnswer(@RequestBody AnswerDTO answerDTO)
    {
        String res = answerService.createAnswer(answerDTO);

        if (res.isEmpty())
        {
            return ResponseEntity.ok("success");
        }
        else
        {
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("/up-vote-answer")
    public ResponseEntity<String> thumbsUp(@RequestBody UserAnswerDTO userAnswerDTO)
    {
        answerService.upVote(userAnswerDTO);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/down-vote-answer")
    public ResponseEntity<String> thumbsDown(@RequestBody UserAnswerDTO userAnswerDTO)
    {
        answerService.downVote(userAnswerDTO);
        return ResponseEntity.ok("success");
    }
}
