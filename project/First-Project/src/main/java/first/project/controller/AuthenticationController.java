package first.project.controller;

import first.project.acessingdatamysql.User;
import first.project.acessingdatamysql.UserRepository;
import first.project.service.AuthenticationService;
import first.project.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("")
    public String Home(Model model)
    {
        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping("/register")
    public String Register(Model model)
    {
        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping("/login")
    public String Login(Model model)
    {
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping("/register-successful")
    public String processRegister(User user, Model model)
    {
        String Error[] = authenticationService.validateAndSave(user);

        if (Error[3].equals("true"))
        {
            return "login";
        } else
        {
            //Probav da isprakam niza, i na google vika deka mozhe, no
            //vo html ne ja chita, dava errori :(
            model.addAttribute("ErrorEmail", Error[0]);
            model.addAttribute("ErrorPassword", Error[1]);
            model.addAttribute("ErrorUsername", Error[2]);
            return "register";
        }
    }
}