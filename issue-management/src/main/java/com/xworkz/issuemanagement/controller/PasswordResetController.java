package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    public PasswordResetController() {
        System.out.println("No param constructor created for PasswordResetController...");
    }

    @PostMapping("reset-password")
    public String passwordReset(RedirectAttributes redirectAttributes, String email, String oldPassword, String newPassword, String confirmPassword) {

        boolean resetSuccessful = passwordResetService.resetPassword(email, oldPassword, newPassword, confirmPassword);
        if (resetSuccessful) {
            System.out.println("Password reset Successful: " + resetSuccessful);
            redirectAttributes.addFlashAttribute("passwordResetMessage", "Password reset successful");
        } else {
            redirectAttributes.addFlashAttribute("passwordResetError", "Failed to reset password. Please check your password");
        }

        return "redirect:/reset-password-page";
    }

    @RequestMapping("reset-password-page")
    public String showResetPasswordPage(Model model) {
        return "PasswordReset";
    }
}


      //  return "PasswordReset";
