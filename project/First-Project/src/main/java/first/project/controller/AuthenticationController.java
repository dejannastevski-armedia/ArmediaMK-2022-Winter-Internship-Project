package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import first.project.dto.UserDTO;
import first.project.exceptions.UserValidationException;
import first.project.model.User;
import first.project.service.AuthenticationService;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("")
    public String FirstPage(Model model)
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
        ArrayList<String> res = authenticationService.validateAndSave(user);
        if (res.isEmpty())
        {
            return "login";
        }
        else
        {
            String result = String.join(", ", res);
            model.addAttribute("res", result);
            return "register";
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<User> processLogin(@RequestBody UserDTO userDTO) throws UserValidationException
    {
        User u = authenticationService.validateAndLogin(userDTO);
        return ResponseEntity.ok(u);
    }
}
