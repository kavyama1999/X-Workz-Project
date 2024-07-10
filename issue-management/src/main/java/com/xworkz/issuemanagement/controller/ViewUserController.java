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

    @Autowired
    private HttpSession httpSession; // Autowire the HttpSession

    public ViewUserController() {
        System.out.println("No parameter constructor in ViewUserController");
    }



    @GetMapping("view-profile") //we have to call this action in profile (link)
    public String showUserDetails(Model model) {
        // Fetch the signed-in user's email from the session
        String userEmail = (String) httpSession.getAttribute("signedInUserEmail");

        // Debugging output to verify the email
        System.out.println("Signed-in user email: " + userEmail);

        if (userEmail != null) {

            // Fetch user data based on the email
            SignUpDTO signUpDTO = viewUserService.getUserByEmail(userEmail);

            // Add the user data to the model
            model.addAttribute("signUpDTO", signUpDTO);
        } else {
            System.out.println("User email not found in session.");
        }

        // Return the view name
        return "ViewUserPage";
    }
}
