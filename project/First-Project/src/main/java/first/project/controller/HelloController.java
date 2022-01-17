package first.project.controller;


import first.project.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HelloController
{

    @RequestMapping(value = "", method = RequestMethod.GET)
    String index(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

}