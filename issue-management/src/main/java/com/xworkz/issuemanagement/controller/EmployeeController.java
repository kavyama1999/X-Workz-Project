package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.emailsending.OTPMailSend;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import com.xworkz.issuemanagement.util.EmailOTPGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private AdminService adminService;


    @Autowired
    private OTPMailSend otpMailSend;




    @Autowired
    private EmailOTPGenerator emailOTPGenerator;  // Inject the OTP generator



    public EmployeeController() {
        log.info("no parameter constructor in EmployeeController ");
    }


    @PostMapping("employeeData")
    public String saveEmployeeData(EmployeeDTO employeeDTO, Model model, RedirectAttributes redirectAttributes) {
        log.info("saveEmployeeData method running in EmployeeController..");
//     boolean saveEmployeeData = employeeService.saveEmployeeData(employeeDTO);

        // Fetch the department by name and to save departmentId in department table

        DepartmentDTO departmentDTO = adminService.findDepartmentByName(employeeDTO.getDepartmentName());

        System.out.println(employeeDTO.getDepartmentName());
        System.out.println("departmentDTO od department name :" +departmentDTO);


        if(departmentDTO!=null) {
            employeeDTO.setDepartmentId(departmentDTO);

            boolean saveEmployeeData = employeeService.saveEmployeeData(employeeDTO);

            if (saveEmployeeData) {
                log.info("saveEmployeeData saved successfully in EmployeeController..");
                redirectAttributes.addFlashAttribute("msg", "Employee data saved successfully..");
                return "redirect:/employeeRegister";
            } else {
                log.info("saveEmployeeData not saved successfully in EmployeeController..");
                redirectAttributes.addFlashAttribute("errorMsg", "Failed to save Employee data");
            }
        }
        return "redirect:/employeeRegister";
    }


    @GetMapping("employeeRegister")   //for load department we have give this action into href link
    public String departmentAdminLogIn(Model model) {
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "EmployeeRegisterPage";
    }


    ///*******************************************************
    @PostMapping("generateOtp")
    public String generateOtp(@RequestParam("emailId") String emailId, Model model,RedirectAttributes redirectAttributes) {
        log.info("generateOtp method running in EmployeeController..");

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);
        if (employeeDTO != null) {
            // Generate OTP
            String otp = emailOTPGenerator.generateOtp();
            System.out.println("OTP : "+otp);

            // Set the OTP and the current time in the employeeDTO
            employeeDTO.setOtp(Long.parseLong(otp));

            // Save the updated employee data with the OTP
            boolean isSaved = employeeService.saveEmployeeData(employeeDTO);

            if (isSaved) {
                // Send OTP to the user's email
                otpMailSend.sendOtpEmail(employeeDTO.getEmailId(), otp);

                log.info("OTP generated and sent to email: {}", emailId);
                redirectAttributes.addFlashAttribute("generatedOTP", "OTP generated and sent to email");
               // return "redirect:/employee-OTP-page";

            } else {
                redirectAttributes.addFlashAttribute("failed", "Failed to generate OTP, please try again.");
                log.error("Failed to save OTP for email: {}", emailId);
            }
        } else {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email not found in the database");
            log.error("Email not found in the database: {}", emailId);
        }

        return "redirect:/employee-OTP-page"; // Return the same view with model attributes
    }


   @GetMapping("employee-OTP-page")
    public String employeeLogin()
    {
        return "EmployeeOTPPage";
    }

    //************************************************************
    //employee login the emailId and otp match then only he should login



    //public String employeeLogin("")

}
