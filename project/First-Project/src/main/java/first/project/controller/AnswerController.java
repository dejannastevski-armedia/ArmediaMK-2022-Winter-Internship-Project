package first.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController
{
    @GetMapping("/view-answer/{id}")
    public String ViewAnswer(@PathVariable String id, Model model)
    {
        model.addAttribute("id", id);
        return "viewAnswer";
    }
}
