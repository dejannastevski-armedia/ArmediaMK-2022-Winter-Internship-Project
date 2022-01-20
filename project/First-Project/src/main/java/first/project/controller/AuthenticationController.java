package first.project.controller;

import first.project.dto.UserDTO;
import first.project.model.User;
import first.project.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

    @GetMapping("/home")
    public String HomePage(Model model)
    {
        return "home";
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

    @PostMapping("/login-successful")
    @ResponseBody
    public ResponseEntity<String> processLogin(@RequestBody UserDTO userDTO)
    {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        ArrayList<String> res = authenticationService.validateAndLogin(user);
        if (res.isEmpty())
        {
            return ResponseEntity.ok("Success");
        } else
        {
            String result = String.join("", res);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}
