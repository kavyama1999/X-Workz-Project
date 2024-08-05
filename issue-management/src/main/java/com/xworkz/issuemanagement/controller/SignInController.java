package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.MailService;
import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
public class SignInController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private HttpSession httpSession; // Autowire the HttpSession

    public SignInController() {
        System.out.println("No parameters in SignInController.. ");
    }

    @PostMapping("sign-in")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        System.out.println("signIn method is running...");

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        SignUpDTO signUpDTO = mailService.findByEmailAndPassword(email, password);
        if (signUpDTO != null) {
            mailService.resetFailedAttempts(email);
            model.addAttribute("wlcm", "Sign_In successful.Welcome, " + signUpDTO.getFirstName());


            // Set the signed-in user's email in the session
            httpSession.setAttribute("signedInUserEmail", email);

            //edit data
            httpSession.setAttribute("signUpDTO", signUpDTO); //also used for saving signUp user id in complaint table


            // Set the profile image in the session
            String profileImageUrl = "/images/" + signUpDTO.getImageName();
            httpSession.setAttribute("profileImage", profileImageUrl);

           // signUpDTO.setImageName("profileicon.jpg");

            model.addAttribute("ProfilePageMessage", "Welcome To Issue Management System, " + signUpDTO.getFirstName());
            return "Profile";
        } else {
            mailService.incrementFailedAttempts(email);
            int failedAttempts = mailService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + ": " + failedAttempts);

            if (failedAttempts >= 3) {
                mailService.lockAccount(email); // Lock account after 3 failed attempts
                System.out.println(email + " :Your account is locked due to too many failed attempts");
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
            } else {
                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                System.out.println("Invalid email Id and password");
                model.addAttribute("accountLocked", false);
            }
            return "SignIn";
        }
    }

    // Forgot password
    @PostMapping("forgot-password")
    public String resetPassword(@RequestParam String email, Model model)
    {
        boolean success = forgotPasswordService.resetPassword(email);
        if (success)
        {
            model.addAttribute("forgotPasswordMessage", "A new password has been sent to your email.");
       return "SignIn";
        }
        else
            {
            model.addAttribute("forgotPasswordError", "Email address not found.");
        }
        return "ForgotPassword";
    }

    @GetMapping("/logout")
    public  String logout(){
        return "index";
    }
}
