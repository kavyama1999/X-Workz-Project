package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.EditUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/") //form action name
@SessionAttributes("signUpDTO")
public class EditUserProfileController {


    @Autowired
    private EditUserProfileService editUserProfileService;

    @Autowired
    private HttpSession httpSession;

    public EditUserProfileController() {
        System.out.println("No  parameters in EditUserProfileController..");
    }


    @GetMapping("edit")
    public String editUserProfile(@RequestParam("email") String email, Model model) {
//        SignUpDTO userData = editUserProfileService.getUserDetails(email);
        String userData = (String) httpSession.getAttribute("signedInUserEmail");

        if (userData != null) {
            //here we can pass SignUpDTO..or method reference
           SignUpDTO signUpDTO= editUserProfileService.getUserDetails(email);

            model.addAttribute("editSignUpDTO", signUpDTO);
//       model.addAttribute("successMessage", "Profile updated successfully");

        }
////        model.addAttribute("errorMessage", "Error updating profile");
        return "EditUserProfile";  //this EditUserProfile jsp file
    }


    @PostMapping("/edit-profile")
    public String updateUserProfile(SignUpDTO signUpDTO, Model model) {
        SignUpDTO userData = editUserProfileService.updateUserDetails(signUpDTO);

        if (userData != null) {
            System.out.println("Updated Data:" + signUpDTO);
            model.addAttribute("signUpDTO", userData);
            model.addAttribute("msg", "Profile updated Successfully!..");

            return "EditUserProfile";
        }
////        model.addAttribute("errorMessage", "Error updating profile");
        return "Profile";
    }
}
