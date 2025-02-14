package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
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

import javax.servlet.http.HttpServletRequest;
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


        // Validate employee email and password
        //EmployeeDTO employee = employeeService.validateLogin(emailId, password);

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);
        if (employeeDTO == null) {
            // If email does not exist, return an error message and redirect to login page
            session.setAttribute("emailId", employeeDTO.getEmailId());
//            session.setAttribute("departmentName", employeeDTO.getDepartmentName());
            session.setAttribute("employeeId", employeeDTO.getEmployeeId());


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

        // Save OTP in employeeDTO and update it in the database
        employeeDTO.setOtp(Long.parseLong(otp));
        boolean isSaved = employeeService.saveEmployeeData(employeeDTO);

        if (isSaved) {
            // Save email in session to use during OTP validation
            session.setAttribute("emailId", emailId);

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


    @GetMapping("employeeLogin")
    public String employeeLoginPage() {
        return "EmployeeLoginPage"; // Display login page
    }

    @GetMapping("employee-OTP-page")
    public String employeeOtpPage() {
        return "EmployeeOTPPage"; // Display OTP entry page
    }


//Validate OTP

    @PostMapping("validateOtp")
    public String validateOtp(@RequestParam("otp") String otp,
                              HttpSession session,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        log.info("validateOtp method running in EmployeeController..");

        // Check if emailId exists in the session
        String sessionEmail = (String) session.getAttribute("emailId");
//        String sessionEmail = (String) session.getAttribute("emailId");

        if (sessionEmail == null) {
            // If session has expired or emailId is null, return an error message
            redirectAttributes.addFlashAttribute("failed", "Session expired. Please try again.");
            return "redirect:/employeeLogin"; // Redirect to login page
        }

        // Retrieve the employeeDTO from the database using the session email
        EmployeeDTO employeeDTO = employeeService.findByEmail(sessionEmail);
        if (employeeDTO == null) {
            // If the employee is not found, redirect to login
            redirectAttributes.addFlashAttribute("failed", "Email not found. Please try again.");
            return "redirect:/employeeLogin";
        }

        // Validate the OTP: Convert OTP string input to Long and compare
        try {
            Long enteredOtp = Long.parseLong(otp);
            if (employeeDTO.getOtp() == null || !employeeDTO.getOtp().equals(enteredOtp)) {
                // If the OTP is invalid, show error on the same OTP page
                model.addAttribute("invalidOtpError", "Invalid OTP, please try again.");
                log.error("Invalid OTP for email: {}", sessionEmail);
                return "EmployeeOTPPage"; // Return back to OTP page with error
            }
        } catch (NumberFormatException e) {
            model.addAttribute("invalidOtpError", "Invalid OTP format.");
            return "EmployeeOTPPage"; // Return back to OTP page with error
        }

        // OTP is valid, clear OTP and proceed to employee profile
        employeeDTO.setOtp(null); // Clear OTP after successful validation
        employeeService.saveEmployeeData(employeeDTO); // Save updated employee data to the database

        // Remove the email from session to avoid re-use of the session

//        session.removeAttribute("emailId");
        // Store email and department name in session
        session.setAttribute("emailId", employeeDTO.getEmailId());
        session.setAttribute("departmentName", employeeDTO.getDepartmentName());
        session.setAttribute("employeeId", employeeDTO.getEmployeeId());


        log.info("Employee successfully logged in with email: {} and department: {}",
                employeeDTO.getEmailId(), employeeDTO.getDepartmentName());


        return "redirect:/employeeProfile"; // Redirect to the employee profile page
    }


    @GetMapping("employeeProfile")
    public String employeeProfilePage(Model model) {
        model.addAttribute("EmployeeProfilePage", "Welcome to Employee profile");//msg shows i employee login page

        return "EmployeeProfilePage"; // Display Employee profile page
    }


    //To resend otp
    @PostMapping("/resendOtp")
    public String resendOtp(HttpSession session, @RequestParam("emailId") String emailId, RedirectAttributes redirectAttributes) {
        // Retrieve email from session
        String email = (String) session.getAttribute("emailId");  //TO RESEND OTP SAME EMAIL TO USE SESSION

        if (emailId == null || emailId.isEmpty()) {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email not found in session.");
            return "redirect:/employeeLogin";
        }

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(email);
        if (employeeDTO == null) {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
            return "redirect:/employeeLogin";
        }

        // Generate new OTP
        String newOtp = emailOTPGenerator.generateOtp();
        log.info("New OTP generated: {}", newOtp);

        // Save the new OTP in employeeDTO and update the database
        employeeDTO.setOtp(Long.parseLong(newOtp));
        boolean isSaved = employeeService.saveEmployeeData(employeeDTO);

        if (isSaved) {
            // Send the new OTP to the user's email
            otpMailSend.sendOtpEmail(employeeDTO.getEmailId(), newOtp);
            redirectAttributes.addFlashAttribute("generatedOTP", "New OTP sent to your email.");
            return "redirect:/employee-OTP-page"; // Redirect back to the OTP page
        } else {
            redirectAttributes.addFlashAttribute("otpError", "Failed to generate a new OTP. Please try again.");
            return "redirect:/employeeLogin";
        }
    }


    //********************************************************************
    //int employeeId,int complaintId
    @PostMapping("/delete-employee-allocation")
    public String deleteEmployeeAllocation(@RequestParam("employeeId") int employeeId, int complaintId, RedirectAttributes redirectAttributes) {
        boolean isDeleted = employeeService.deleteAllocatedEmployee(employeeId, complaintId);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Employee allocation deleted and status set to inactive.");
            return "redirect:/department-admin-view-particular-department";

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employee allocation.");
        }

//        return "redirect:/view-complaints";
        return "redirect:/department-admin-view-particular-department";

    }


    @GetMapping("employee-view-particular-department")
    public String getParticularDepartment(Model model, HttpServletRequest httpServletRequest) {
        log.info("getParticularDepartment method running in EmployeeController");

        // Retrieve the current session
        HttpSession session = httpServletRequest.getSession();

        // Retrieve the employeeId from the session
//        Integer employeeId = (Integer) session.getAttribute("employeeId");
        String getEmail = (String) session.getAttribute("emailId");

        // Validate employeeId
        if (getEmail == null) {
            log.error("Employee ID not found in session. Redirecting to error page.");
            model.addAttribute("errorMessage", "Employee ID not found. Please log in again.");
            return "EmployeeViewParticularComplaint";
        }

        log.info("Retrieved employeeId from session: {}", getEmail);

        // Fetch the complaints assigned to the logged-in employee
        List<RaiseComplaintDTO> complaintData = employeeService.getParticularDepartments(getEmail);

        // Check if data exists
        if (complaintData != null && !complaintData.isEmpty()) {
            log.info("Successfully fetched complaints for employeeId: {}", getEmail);
            model.addAttribute("particularDepartment1", complaintData);
            return "EmployeeViewParticularComplaint"; // Return the JSP page to display the data
        } else {
            log.warn("No complaints found for employeeId: {}", getEmail);
            model.addAttribute("errorMessage1", "No complaints found for your account.");
            return "EmployeeViewParticularComplaint"; // Redirect to an error page with an appropriate message
        }
    }


    @PostMapping("update-complaintId")
    public String updateStatusInComplaintRaiseTable(@RequestParam("complaintId") int complaintId,
                                                    @RequestParam("complaintStatus") String complaintStatus,
                                                    Model model, RedirectAttributes redirectAttributes) {
        // Update the RaiseComplaint assignment for the complaint

        employeeService.updateStatusRaiseComplaintAndNotifyUser(complaintId, complaintStatus);

        log.info("Updated successfully in controller..");

        // Add success message (optional)
        redirectAttributes.addFlashAttribute("success", "Complaint status updated successfully!");


        // Redirect to the GET method which fetches data and displays the updated page
        return "redirect:employee-view-particular-department";
    }


    @PostMapping("/submit-feedback")
    public String submitFeedback(@RequestParam("complaintId") int complaintId,
                                 @RequestParam("feedbackText") String feedbackText,
                                 RedirectAttributes redirectAttributes) {
        log.info("submitFeedback method running in EmployeeController");

        employeeService.updateUserFeedback(complaintId, feedbackText);

        redirectAttributes.addFlashAttribute("success", "Feedback submitted successfully!");

//        return "redirect:employee-view-particular-department";
        return "redirect:/submit-feedback-page";
    }



    @GetMapping("/submit-feedback-page")
    public String showFeedbackPage(@RequestParam("complaintId") int complaintId, Model model) {
        model.addAttribute("complaintId", complaintId);
        return "SubmitUserFeedback"; // The JSP page name
    }

}


//    @PostMapping("/delete-employee-allocation")
//    public String deleteEmployeeAllocation(@RequestParam("complaintId") Long complaintId, RedirectAttributes redirectAttributes) {
//        // Fetch employee name based on complaintId
//        String employeeName = employeeService.deleteAllocatedEmployee(e);
//
//        if (employeeName != null) {
//            boolean isDeleted = employeeService.deleteAllocatedEmployee(employeeName);
//            if (isDeleted) {
//                redirectAttributes.addFlashAttribute("successMessage", "Employee allocation deleted and status set to inactive.");
//            } else {
//                redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employee allocation.");
//            }
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "No employee found for the given complaint.");
//        }
//
//        return "redirect:/department-admin-view-particular-department";
//    }