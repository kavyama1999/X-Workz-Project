package com.xworkz.issuesmanagement.controller;



import com.xworkz.issuesmanagement.dto.SignUpDTO;
import com.xworkz.issuesmanagement.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController  //which give particular data
@RequestMapping("/")
public class AjaxEmailDuplicate {


    @Autowired
    private SignUpService signUpService;

    @GetMapping("validateEmail/{email}")
    public String validateEmail(@PathVariable String email) {
        System.out.println("email:"+email);
        SignUpDTO signUpDTO=signUpService.findByExistsEmail(email);
        System.out.println("validateEmail method running in AjaxEmailDuplicate....");

        return signUpDTO !=null ? "Email already exists" : "";
    }
}
