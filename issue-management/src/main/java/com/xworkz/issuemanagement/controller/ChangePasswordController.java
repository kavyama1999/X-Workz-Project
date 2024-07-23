
package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.model.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ChangePasswordController {


    @Autowired
    private ChangePasswordService changePasswordService;


    @GetMapping("ChangePasswordPage")
    public String ChangePasswordPage(){
        return "ChangePassword";
    }


    public ChangePasswordController() {
        System.out.println("No param constructor created for ChangePasswordController...");
    }


    @PostMapping("reset-password")
    public String passwordReset(Model model, @RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword) {

        boolean resetSuccessful = changePasswordService.changePassword(email, oldPassword, newPassword, confirmPassword);
        if (resetSuccessful)
        {
            System.out.println("Password reset Successful:"+resetSuccessful);
            model.addAttribute("changePasswordMessage", "Password reset successful");

        }
        else
        {
            model.addAttribute("changePasswordError", "Failed to reset password.Please check your password");
             return "ChangePassword";

        }

       // return "ChangePassword";
        return "SignIn";
    }
}
