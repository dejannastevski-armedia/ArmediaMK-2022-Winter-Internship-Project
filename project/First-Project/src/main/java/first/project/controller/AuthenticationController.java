package first.project.controller;

import first.project.dto.QuestionDTO;
import first.project.dto.UserDTO;
import first.project.exceptions.UserValidationException;
import first.project.model.User;
import first.project.service.AuthenticationService;
import first.project.service.QuestionService;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
        } else
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
