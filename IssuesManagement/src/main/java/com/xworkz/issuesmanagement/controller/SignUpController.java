package com.xworkz.issuesmanagement.controller;

import com.xworkz.issuesmanagement.dto.SignUpDTO;
import com.xworkz.issuesmanagement.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    public SignUpController() {
        System.out.println("No parameters Constructor created for SignUpController");
    }

    @PostMapping("/sign-up")
    public String signInform(@Valid SignUpDTO signUpDTO, BindingResult bindingResult, Model model, @RequestParam("email") String email) {
        System.out.println("SignUp Data:" + signUpDTO);

        // Generate automatic password before validation
        String generatedPassword = signUpService.generateRandomPassword();
        signUpDTO.setPassword(generatedPassword);

        if (bindingResult.hasErrors()) {
            System.out.println("SignUpDTO has invalid data:" + signUpDTO);
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("signUpDTO", signUpDTO);
            return "SignUpForm";
        } else {
            boolean dataValid = this.signUpService.saveAndValidate(signUpDTO);
            if (dataValid) {
                System.out.println("SignUpService registration successful in SignUpController :" + signUpDTO);

                // Send email with generated password
                String subject = "welcome to our Issue management";

                String body = "Hi" + signUpDTO.getFirstName() + ",\n\n Your registration is successful.  Your password is  " + signUpDTO.getPassword();

                signUpService.sendPasswordEmail(email, subject, body);

                model.addAttribute("msg", "Signup successful. Please check your email for your password.");
                return "LoginPage";
            } else {
                System.out.println("SignUpService registration not successful in SignUpController : " + signUpDTO);
               // model.addAttribute("failedMsg", "SignUp failed. Please try again. \"This email address is already in use.\"\n");
                model.addAttribute("msg", "SignUp failed. Please try again.");

                return "SignUpForm";
            }
        }
    }


    @PostMapping("login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        System.out.println("login method is running...");

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        SignUpDTO signUpDTO = signUpService.findByEmailAndPassword(email, password);
        if (signUpDTO != null) {
//            SignUpService.resetFailedAttempts(email);

            signUpService.resetFailedAttempts(email);
            model.addAttribute("wlcm", "Login successful. Welcome, " + signUpDTO.getFirstName());
            return "WelcomePage";
        } else {
//            System.out.println("Invalid email or password for email: " + email);
//            model.addAttribute("error", "Invalid email or password. Please try again.");
//            return "LoginPage";

            signUpService.incrementFailedAttempts(email);
            int failedAttempts = signUpService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + ": " + failedAttempts);
//                if (failedAttempts >3) {
//
//                    //button disabled
//
//                    signUpService.lockAccount(email);
//                    model.addAttribute("error", "Your account is locked due to too many failed attempts.");
//                } else {
//                    model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
//                }
            if (failedAttempts >= 3) {
                signUpService.lockAccount(email); // Lock account after 3 failed attempts
                System.out.println(email+" :Your account is locked due to too may failed attempts");
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
            } else {

                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                System.out.println("Invalid email Id and password");
                model.addAttribute("accountLocked", false);
            }


            return "LoginPage";
        }
    }
}


