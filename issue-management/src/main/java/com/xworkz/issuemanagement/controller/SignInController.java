package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.MailService;
import com.xworkz.issuemanagement.model.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class SignInController {


    @Autowired
    private MailService mailService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    public SignInController() {
        System.out.println("No parameters in SignInController.. ");
    }


    @PostMapping("sign-in")
    public String signIn(SignUpDTO signUpDTO, @RequestParam String email, @RequestParam String password, Model model) {
        System.out.println("signIn method is running...");


        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        SignUpDTO signUpDTO1 = mailService.findByEmailAndPassword(email, password);
        if (signUpDTO1 != null)

        {

            mailService.resetFailedAttempts(email);
            model.addAttribute("wlcm", "Sign_In successful.Welcome, " + signUpDTO1.getFirstName());
            return "WelcomePage";
        }
        else

        {
            mailService.incrementFailedAttempts(email);
            int failedAttempts = mailService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + ": " + failedAttempts);


            if (failedAttempts >= 3) {
                mailService.lockAccount(email); // Lock account after 3 failed attempts
                System.out.println(email + " :Your account is locked due to too may failed attempts");
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
            }

            else

            {

                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                System.out.println("Invalid email Id and password");
                model.addAttribute("accountLocked", false);
            }
            return "SignIn";
        }
    }



    //Reset password

    @PostMapping("forgot-password")
    public String resetPassword(@RequestParam String email, Model model) {
        boolean success = resetPasswordService.resetPassword(email);
        if (success) {
            model.addAttribute("resetMessage", "A new password has been sent to your email.");
        } else {
            model.addAttribute("resetError", "Email address not found.");
        }
        return "ResetPassword";
    }
}



