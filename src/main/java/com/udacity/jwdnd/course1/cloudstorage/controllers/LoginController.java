package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String getLogin(@ModelAttribute LoginForm loginForm, Model model){
        model.addAttribute("loginForm",loginForm);
        return "login";
    }

    @GetMapping("/error")
    public String getLoginError( HttpServletRequest request, @ModelAttribute LoginForm loginForm, Model model){
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)!=null){
            model.addAttribute("invalidCredentials",true);
        }
        model.addAttribute("loginForm",loginForm);
        return "login";
    }

    @PostMapping
    public String postLogin(HttpServletRequest request, LoginForm loginForm, Model model){
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)!=null){
            model.addAttribute("invalidCredentials",true);
        }
        model.addAttribute("loginForm",loginForm);
        return "login";
    }
}
