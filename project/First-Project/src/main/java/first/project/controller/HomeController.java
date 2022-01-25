package first.project.controller;

import first.project.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController
{
    @RequestMapping(value = "", method = RequestMethod.GET)
    String index(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/home")
    public String HomePage(Model model)
    {
        return "home";
    }
}