package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//to avoid duplicate entries of email I used Ajax separate controller

//@RestController is to

@RestController
@RequestMapping("/")

public class AjaxEmailController {

    @Autowired
    private AjaxService ajaxService;


    public AjaxEmailController() {
        System.out.println("Created AjaxEmailController..");
    }

    //get_mapping is used to map the action "validateEmail" in init class

    @GetMapping("/validateEmail/{email}")
    public String emailValidation(@PathVariable String email) {


        //this print statement is to print email in console
        System.out.println(email);

        //this is to check whether email is exits or not
        if (ajaxService.existsByEmail(email)) {
            return "<span style='color:red;'>Email Already exists</span>";
        } else {
            return null;
        }
    }


//    @GetMapping("/validateContactNumber/{contactNumber}")
//    public String contactNumberValidation(@PathVariable Long contactNumber) {
//        System.out.println(contactNumber);
//        if (signUpService.existsByContactNumber(contactNumber)) {
//            return "<span style='color:red;'>Contact Number Already exists</span>";
//        } else {
//            return null;
//        }
//    }


    @GetMapping("/validateContactNumber/{contactNumber}")
    public String contactNumberValidation(@PathVariable Long contactNumber) {
        System.out.println(contactNumber);
        if (ajaxService.existsByContactNumber(contactNumber)) {
            return "<span style='color:red;'>Contact Number Already exists</span>";
        } else {
            return null;
        }
    }




    //*********************************************************
    //subAdmin registration

    @GetMapping("/subAdminValidateEmail/{email}")
    public String subAdminEmailValidation(@PathVariable String email) {


        //this print statement is to print email in console
        System.out.println(email);

        //this is to check whether email is exits or not
        if (ajaxService.existsByEmailId(email)) {
            return "<span style='color:red;'>Email Already exists</span>";
        } else {
            return null;
        }

    }



        @GetMapping("/subAdminValidateContactNumber/{contactNumber}")
        public String subAdminContactNumberValidation(@PathVariable Long contactNumber) {
            System.out.println(contactNumber);
            if (ajaxService.checkExistsByContactNumber(contactNumber))
            {
                return "<span style='color:red;'>Contact Number Already exists</span>";
            } else {
                return null;
            }
        }

}
