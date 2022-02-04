package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import first.project.dto.QuestionDTO;
import first.project.dto.UserQuestionDTO;
import first.project.exceptions.InvalidCreatorException;
import first.project.service.QuestionService;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController
{
    @Autowired
    private QuestionService questionService;

    @PostMapping("/ask-question")
    @ResponseBody
    public ResponseEntity<String> askQuestion(@RequestBody QuestionDTO questionDTO)
    {
        ArrayList<String> res = questionService.validateAndPost(questionDTO);
        if (res.isEmpty())
        {
            return ResponseEntity.ok("Success");
        }
        else
        {
            String result = String.join("<br>", res);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/delete-question")
    @ResponseBody
    public ResponseEntity<String> deleteQuestion(@RequestBody UserQuestionDTO userQuestionDTO) throws InvalidCreatorException
    {
        questionService.deleteQuestion(userQuestionDTO);
        return ResponseEntity.ok("Success");
    }
}
