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


public ViewUserController()
{
    System.out.println("No parameter constructor in ViewUserController");
}

    @GetMapping("/view-profile")
    public String viewProfile(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email != null) {
            SignUpDTO signUpDTO = viewUserService.getUserByEmail(email);
            model.addAttribute("signUpDTO", signUpDTO);
            return "ViewUserPage";
        } else {
            return "redirect:/SignIn.jsp";
        }
    }
}

