package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return "viewAnswer";
    }

    @PostMapping("/answer-successful")
    @ResponseBody
    public ResponseEntity<String> postAnswer(@RequestBody AnswerDTO answerDTO)
    {
        ArrayList<String> res = answerService.validateAndPost(answerDTO);
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
}
