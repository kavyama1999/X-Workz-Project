@PostMapping("validateOtp")
public String validateOtp(@RequestParam("otp") String otp,
                          HttpSession session,
                          Model model,
                          RedirectAttributes redirectAttributes) {
    log.info("validateOtp method running in EmployeeController..");

    // Check if emailId exists in the session
    String sessionEmail = (String) session.getAttribute("emailId");
    if (sessionEmail == null) {
        redirectAttributes.addFlashAttribute("failed", "Session expired. Please try again.");
        return "redirect:/employeeLogin";
    }

    // Retrieve the employeeDTO from the database using the session email
    EmployeeDTO employeeDTO = employeeService.findByEmail(sessionEmail);
    if (employeeDTO == null) {
        redirectAttributes.addFlashAttribute("failed", "Email not found. Please try again.");
        return "redirect:/employeeLogin";
    }

    // Validate the OTP
    try {
        Long enteredOtp = Long.parseLong(otp);
        if (employeeDTO.getOtp() == null || !employeeDTO.getOtp().equals(enteredOtp)) {
            model.addAttribute("invalidOtpError", "Invalid OTP, please try again.");
            log.error("Invalid OTP for email: {}", sessionEmail);
            return "EmployeeOTPPage";
        }
    } catch (NumberFormatException e) {
        model.addAttribute("invalidOtpError", "Invalid OTP format.");
        return "EmployeeOTPPage";
    }

    // OTP is valid, clear OTP and proceed
    employeeDTO.setOtp(null); // Clear OTP after successful validation
    employeeService.saveEmployeeData(employeeDTO); // Save updated employee data

    // Store email and department name in session
    session.setAttribute("emailId", employeeDTO.getEmailId());
    session.setAttribute("departmentName", employeeDTO.getDepartmentName());

    log.info("Employee successfully logged in with email: {} and department: {}",
             employeeDTO.getEmailId(), employeeDTO.getDepartmentName());

    return "redirect:/employeeProfile";
}


////////////////////////////////////////////////////////////////////////////////////
@GetMapping("employee-view-particular-department")
public String getParticularDepartment(Model model, HttpServletRequest request) {
    log.info("getParticularDepartment method running in EmployeeController");

    HttpSession session = request.getSession();
    String departmentName = (String) session.getAttribute("departmentName");

    if (departmentName == null || departmentName.isEmpty()) {
        log.error("Department name not found in session. Redirecting to error page.");
        return "error-page"; // Redirect to an error page if departmentName is missing
    }

    // Fetch complaints for the department
    List<RaiseComplaintDTO> complaints = employeeService.getParticularDepartments(departmentName);

    if (complaints != null && !complaints.isEmpty()) {
        log.info("Successfully fetched complaints for department: {}", departmentName);
        model.addAttribute("particularDepartment", complaints);
        return "EmployeeViewParticularComplaint"; // Display complaints
    } else {
        log.error("No complaints found for department: {}", departmentName);
        model.addAttribute("errorMessage", "No complaints found for your department.");
        return "EmployeeViewParticularComplaint"; // Display message on the same page
    }
}
