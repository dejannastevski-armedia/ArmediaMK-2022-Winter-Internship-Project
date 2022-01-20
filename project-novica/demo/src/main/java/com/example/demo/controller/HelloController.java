package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping("/home")
    public String helloWorld(Model model)
    {
        model.addAttribute("user", new User());
        return "home";
    }

//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<String> add (User user){
//        String res= String.valueOf(authenticationService.login(user.getEmail(), user.getPassword()));
//        if(/*authenticationService.login(user.getEmail(), user.getPassword()).isEmpty()/*/res.length()==0){
//            return ResponseEntity.ok("success");
//        }
//        else{
//           return ResponseEntity.badRequest().body("Please enter valid credentials");
//        }
//    }
}
