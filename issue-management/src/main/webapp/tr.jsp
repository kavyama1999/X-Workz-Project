@PostMapping("validateOtp")
public String validateOtp(@RequestParam("otp") String otp,
                          HttpSession session,
                          Model model,
                          RedirectAttributes redirectAttributes) {
    log.info("validateOtp method running in EmployeeController..");

    String sessionEmail = (String) session.getAttribute("emailId");
    if (sessionEmail == null) {
        redirectAttributes.addFlashAttribute("failed", "Session expired. Please try again.");
        return "redirect:/employeeLogin";
    }

    EmployeeDTO employeeDTO = employeeService.findByEmail(sessionEmail);
    if (employeeDTO == null) {
        redirectAttributes.addFlashAttribute("failed", "Email not found. Please try again.");
        return "redirect:/employeeLogin";
    }

    try {
        Long enteredOtp = Long.parseLong(otp);
        if (employeeDTO.getOtp() == null || !employeeDTO.getOtp().equals(enteredOtp)) {
            model.addAttribute("invalidOtpError", "Invalid OTP, please try again.");
            return "EmployeeOTPPage";
        }
    } catch (NumberFormatException e) {
        model.addAttribute("invalidOtpError", "Invalid OTP format.");
        return "EmployeeOTPPage";
    }

    employeeDTO.setOtp(null);
    employeeService.saveEmployeeData(employeeDTO);

    session.setAttribute("emailId", employeeDTO.getEmailId());
    session.setAttribute("departmentName", employeeDTO.getDepartmentName());

    return "redirect:/employeeProfile";
}

@GetMapping("employeeProfile")
public String employeeProfilePage(Model model) {
    model.addAttribute("EmployeeProfilePage", "Welcome to Employee profile");
    return "EmployeeProfilePage";
}

@PostMapping("/resendOtp")
public String resendOtp(HttpSession session, RedirectAttributes redirectAttributes) {
    String email = (String) session.getAttribute("emailId");
    if (email == null || email.isEmpty()) {
        redirectAttributes.addFlashAttribute("emailNotFound", "Email not found in session.");
        return "redirect:/employeeLogin";
    }

    EmployeeDTO employeeDTO = employeeService.findByEmail(email);
    if (employeeDTO == null) {
        redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
        return "redirect:/employeeLogin";
    }

    String newOtp = emailOTPGenerator.generateOtp();
    log.info("New OTP generated: {}", newOtp);

    employeeDTO.setOtp(Long.parseLong(newOtp));
    boolean isSaved = employeeService.saveEmployeeData(employeeDTO);

    if (isSaved) {
        otpMailSend.sendOtpEmail(employeeDTO.getEmailId(), newOtp);
        redirectAttributes.addFlashAttribute("generatedOTP", "New OTP sent to your email.");
        return "redirect:/employee-OTP-page";
    } else {
        redirectAttributes.addFlashAttribute("otpError", "Failed to generate a new OTP. Please try again.");
        return "redirect:/employeeLogin";
    }
}

@GetMapping("employee-view-particular-department")
public String getParticularDepartment(Model model, HttpServletRequest httpServletRequest) {
    HttpSession httpSession = httpServletRequest.getSession();
    String departmentName = (String) httpSession.getAttribute("departmentName");

    if (departmentName == null || departmentName.isEmpty()) {
        return "error-page";
    }

    List<RaiseComplaintDTO> getData = employeeService.getParticularDepartments(departmentName);

    if (getData != null) {
        model.addAttribute("particularDepartment", getData);
        return "EmployeeViewParticularComplaint";
    } else {
        model.addAttribute("errorMessage", "No complaints found for your department.");
        return "EmployeeViewParticularComplaint";
    }
}
