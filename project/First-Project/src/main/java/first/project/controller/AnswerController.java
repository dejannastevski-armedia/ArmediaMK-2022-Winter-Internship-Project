package first.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController
{
    @GetMapping("/view-answer/{id}")
    public String ViewAnswer()
    {
        return "viewAnswer";
    }
}
