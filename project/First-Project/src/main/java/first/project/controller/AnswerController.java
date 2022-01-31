package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;
import first.project.exceptions.InvalidAnswerException;
import first.project.model.Answer;
import first.project.service.AnswerService;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController
{
    @Autowired
    private AnswerService answerService;

    @GetMapping("/view-answer/{id}")
    public String ViewAnswer(@PathVariable String id, Model model)
    {
        model.addAttribute("id", id);
        ArrayList<Answer> answerList = answerService.getAllAnswersById(Integer.parseInt(id));
        model.addAttribute("answerList", answerList);
        return "viewAnswer";
    }

    @PostMapping("/answer-successful")
    @ResponseBody
    public ResponseEntity<Answer> postAnswer(@RequestBody AnswerDTO answerDTO) throws InvalidAnswerException
    {
        Answer answer = answerService.validateAndSave(answerDTO);
        return ResponseEntity.ok(answer);
    }
}
