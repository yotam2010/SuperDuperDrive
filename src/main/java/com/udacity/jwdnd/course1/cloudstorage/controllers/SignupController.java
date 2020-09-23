package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String getSignup(SignupForm signupForm, Model model){
        return "signup";
    }

    @PostMapping
    public String postSignup(SignupForm signupForm, Model model){
        Integer userId = userService.createUser(signupForm);
        if(userId==null){
            model.addAttribute("failedToCreateUser",true);
            return "signup";
        }
        model.addAttribute("succeedToCreateUser",true);
        return "signup";
    }
}
