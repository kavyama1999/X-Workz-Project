package com.xworkz.issuemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String homePage() {
        return "Home";
    }

    @GetMapping("HomePage")
    public String HomePage() {
        return "Home";
    }


    @GetMapping("SignInPage")
    public String SignInPage() {
        return "SignIn";
    }

    @GetMapping("SignUpPage")
    public String SignUp() {
        return "SignUp";
    }

    @GetMapping("ForgotPasswordPage")
    public String ForgotPasswordPage() {
        return "ForgotPassword";
    }


    //raise complaint

    @GetMapping("Raise_Complaint")
    public String raiseComplaint()
    {
        return "RaiseComplaint";
    }

    @GetMapping("Admin")
    public String Admin() {
        return "AdminPage";
    }



    //search
    @GetMapping("search-by-complaint")
    public String searchComplaint()
    {
        return "SearchRaiseComplaint";
    }


    //add department


    @GetMapping("add-complaints")
    public String addDepartmentByAdmin()
    {
        return "AdminAddComplaints";
    }



    //register department admin

    @GetMapping("addDepartmentPage")
    public String registerDepartmentAdmin()
    {
        return "RegisterDepartmentAdmin";
    }
}
