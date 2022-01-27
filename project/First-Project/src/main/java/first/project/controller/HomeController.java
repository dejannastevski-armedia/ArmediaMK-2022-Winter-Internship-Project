package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import first.project.model.Question;
import first.project.model.User;
import first.project.service.QuestionService;

@Controller
public class HomeController
{
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    String index(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/home")
    public String HomePage(Model model)
    {
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList", questionList);
        return "home";
    }
}