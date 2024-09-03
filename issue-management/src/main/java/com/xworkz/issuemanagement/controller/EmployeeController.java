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

import javax.servlet.http.HttpSession;
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
    private HttpSession httpSession;


    @Autowired
    private EmailOTPGenerator emailOTPGenerator;  // Inject the OTP generator


    public EmployeeController() {
        log.info("no parameter constructor in EmployeeController ");
    }

    //employee register
    @PostMapping("employeeData")
    public String saveEmployeeData(EmployeeDTO employeeDTO, Model model, RedirectAttributes redirectAttributes) {
        log.info("saveEmployeeData method running in EmployeeController..");
//     boolean saveEmployeeData = employeeService.saveEmployeeData(employeeDTO);

        // Fetch the department by name and to save departmentId in department table

        DepartmentDTO departmentDTO = adminService.findDepartmentByName(employeeDTO.getDepartmentName());

        System.out.println(employeeDTO.getDepartmentName());
        System.out.println("departmentDTO od department name :" + departmentDTO);


        if (departmentDTO != null) {
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
    //employee login
    // Employee Login with CAPTCHA validation
    // Employee Login with CAPTCHA and OTP generation
    @PostMapping("generateOtp")
    public String generateOtp(@RequestParam("emailId") String emailId,
                              @RequestParam("captcha") String captchaInput,
                              HttpSession session,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        log.info("generateOtp method running in EmployeeController..");

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);
        if (employeeDTO == null) {
            // If email does not exist, return an error message and redirect to login page
            redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
            log.error("Email not found in the database: {}", emailId);
            return "redirect:/employeeLogin";
        }

        // Validate CAPTCHA
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equals(captchaInput)) {
            // CAPTCHA is invalid, return an error message
            redirectAttributes.addFlashAttribute("captchaError", "Invalid CAPTCHA.");
            log.error("Invalid CAPTCHA for email: {}", emailId);
            return "redirect:/employeeLogin";
        }

        // CAPTCHA is valid, proceed with OTP generation
        String otp = emailOTPGenerator.generateOtp();
        log.info("OTP generated: {}", otp);

        // Save OTP in employeeDTO and update in the database
        employeeDTO.setOtp(Long.parseLong(otp));
        boolean isSaved = employeeService.saveEmployeeData(employeeDTO);

        if (isSaved) {
            // Send OTP to the user's email
            otpMailSend.sendOtpEmail(employeeDTO.getEmailId(), otp);
            redirectAttributes.addFlashAttribute("generatedOTP", "OTP generated and sent to email.");
            log.info("OTP generated and sent to email: {}", emailId);
            return "redirect:/employee-OTP-page";
        } else {
            redirectAttributes.addFlashAttribute("failedToGenerateOTPError", "Failed to generate OTP, please try again.");
            log.error("Failed to save OTP for email: {}", emailId);
        }

        return "redirect:/employeeLogin"; // Redirect back to login page if there is an error
    }

    @GetMapping("employee-OTP-page")
    public String employeeOtpPage() {
        return "EmployeeOTPPage"; // Display OTP entry page
    }

    @GetMapping("employeeLogin")
    public String employeeLoginPage() {
        return "EmployeeLoginPage"; // Display login page
    }



//*********************************************************
//    //when i select allocate employeeName that id should saved to save in Complaint raise table

@PostMapping("update-employeeId")
public String updateEmployeeId(@RequestParam("complaintId") int complaintId,
                               @RequestParam("employeeId") int employeeId,
                               @RequestParam ("status")String status, Model model)
{
    System.out.println("updated employeeId");

    System.out.println("Received complaintId: " + complaintId);
    System.out.println("Received employeeId: " + employeeId);
    System.out.println("Received status: " + status);


    employeeService.updateEmployeeId(complaintId,employeeId);
    employeeService.updateEmployeeStatus(employeeId,status);

    model.addAttribute("updated-employeeId","Employee Allocated Successfully");

    return "DepartmentAdminViewComplaintRaiseDetails";
}


}



