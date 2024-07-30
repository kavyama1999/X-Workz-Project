package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.MailService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;


//@Component
@Controller
@RequestMapping("/")
@Slf4j
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private MailService mailService;

    public SignUpController() {
        //System.out.println("No param constructor created for SignUpController..");
        log.info("No param constructor created for SignUpController... ");
        //log.info("hgdfhdg{}",name);
    }


    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpDTO signUpDTO, BindingResult bindingResult, Model model, @RequestParam("email") String email , RedirectAttributes redirectAttributes) {
        System.out.println("signUp method running in SignUpController..");
        System.out.println("SignUpDTO :" + signUpDTO);
        if (bindingResult.hasErrors()) {
            System.out.println("SignUpDTO has invalid data");
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("signUpDTO", signUpDTO); //this retaining values form form page
            return "SignUp";


        } else {


            boolean dataValid = this.signUpService.saveAndValidate(signUpDTO);
            if (dataValid) {
                System.out.println("SignUpService registration successful in SignUpController:" + signUpDTO);


                signUpDTO.setImageName("profileicon.jpg");

//                // Send email with generated password
//                String subject = "welcome to our Issue management";
//
//                String body = "Hi" + signUpDTO.getFirstName() + ",\n\n Your registration is successful.  Your password is  " + signUpDTO.getPassword();
//
//                mailService.sendPasswordEmail(email, subject, body);
//
               model.addAttribute("msg", "Signup successful. " + signUpDTO.getFirstName() + " Please check your email for your password.");
                //return "SignUp";
                //return "redirect:/SignUp";
                return "redirect:/sign-up-success"; // Redirect to avoid form resubmission


            } else {
                System.out.println("SignUpService registration not successful in SignUpController:" + signUpDTO);
            }
            model.addAttribute("msg", "Registration successful :" + signUpDTO.getFirstName());
           // return "SignUp";
            return "redirect:/sign-up-success"; // Redirect to avoid form resubmission


        }

    }


    @GetMapping("/sign-up-success")
    public String signUpSuccess(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO, Model model,RedirectAttributes redirectAttributes) {
        model.addAttribute("msg", "Signup successful " + signUpDTO.getFirstName() + " Please check your email for your password.");

        // Return the success view
        return "SignUp";
    }
}

