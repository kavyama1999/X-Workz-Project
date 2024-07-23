package com.xworkz.issuemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {



    @RequestMapping("/")
    public String homePage(){
        return "Home";
    }

    @GetMapping("HomePage")
    public String HomePage(){
        return "Home";
    }

    @GetMapping("SignInPage")
    public String SignInPage(){
        return "SignIn";
    }

    @GetMapping("SignUpPage")
    public String SignUp(){
        return "SignUp";
    }

    @GetMapping("ForgotPasswordPage")
    public String ForgotPasswordPage(){
        return "ForgotPassword";
    }





}