package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import first.project.model.Question;
import first.project.model.User;
import first.project.service.AuthenticationService;
import first.project.service.QuestionService;

@Controller
public class HomeController
{
    @Autowired
    private QuestionService questionService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    String index(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/home")
    public String HomePage(Model model, Authentication authentication)
    {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
        {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = authenticationService.findByEmail(userDetails.getUsername());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userId", user.getId());
            List<Question> questionList = questionService.getAllQuestions();
            model.addAttribute("questionList", questionList);
            return "home";
        }
        else
        {
            return "login";
        }
    }
}