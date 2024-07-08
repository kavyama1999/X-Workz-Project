package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.ViewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")

public class ViewUserController {


    @Autowired
    private ViewUserService viewUserService;


    public ViewUserController() {
        System.out.println("No parameter constructor in ViewUserController");
    }

    @GetMapping("/view-profile")
    public String showProfile(Model model) {
        // Assuming you have a method to get the currently logged-in user's email
        String userEmail = userService.getLoggedInUserEmail();

        // Fetch user data based on the email
        SignUpDTO signUpDTO = userService.getUserByEmail(userEmail);

        // Add the user data to the model
        model.addAttribute("signUpDTO", signUpDTO);

        // Return the view name
        return "profile";
    }

}