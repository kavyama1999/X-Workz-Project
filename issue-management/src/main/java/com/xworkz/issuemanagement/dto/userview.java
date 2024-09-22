////<%@ page isELIgnored="false"%>
////<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
////
////<!DOCTYPE html>
////<html>
////<head>
////    <meta charset="ISO-8859-1">
////
////     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
////    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
////    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
////
////
////<!--This for to adjust drop down button color!-->
////<style>
////        .dropdown-toggle-custom {
////    color: #fff;
////    background-color: transparent;
////    border: 1px solid #fff;
////    padding: 5px 10px;
////    border-radius: 5px;
////}
////        .dropdown-toggle-custom:hover {
////    background-color: #495057; /* Darker grey for hover */
////}
////    </style>
////
////
////    <style>
////
////    .search-container {
////    text-align: right;
////    margin-bottom: 20px; /* Adjust as needed for spacing */
////}
////
////    .search-button {
////    padding: 10px 20px; /* Adjust as needed for button size */
////    font-size: 16px; /* Adjust as needed for font size */
////}
////
////.status-select {
////    width: 180px; /* Adjust as needed for desired width */
////}
////
////
////.card-size{
////    width: 1400px; /* Adjust as needed for desired width */
////}
////
////    </style>
////
////
////</head>
////<body>
////<nav class="navbar navbar-dark bg-dark">
////    <div class="container-fluid">
////        <div class="navbar-header">
////            <a class="navbar-brand" href="#">
////                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
////            </a>
////            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
////            <a class="navbar-brand text-white" href="Admin"><b>Admin</b></a>
////        </div>
////
////
////
////        <div class="dropdown">
////                    <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
////Admin
////        </button>
////                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
////
////                    <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>
////
////
////                    </ul>
////                </div>
////
////    </div>
////</nav>
////
////
////
////
////
////<div class="container mt-5 mb-5">
////    <div class="card card-size">
////
////        <div class="card-header">
////            <h3><b>View Raise complaint Details  </b></h3>
////
////
////   <div class="container">
////        <div class="search-container">
////            <a class="btn btn-dark search-button" href="search-by-complaint"><strong>Search</strong></a>
////        </div>
////
////
////
////
////
////
////
////        <div class="card-body">
////            <table class="table table-bordered">
////                <thead>
////                <tr>
////<th>Serial Number</th>
////                    <th>ID</th>
////<th>Complaint Type</th>
////                    <th>Country</th>
////                    <th>State </th>
////                    <th>City </th>
////                    <th>Area</th>
////                    <th>Address</th>
////                    <th>Description</th>
////<th>User Id</th>
////<th>Allocate Department</th>
////                    <th>Status</th>
////                    <th>Submit</th>
////
////
////                </tr>
////                </thead>
////                <tbody>
////                <c:forEach var="viewRaiseComplaintUsers" items="${viewRaiseComplaint}" varStatus="status">
////                    <tr>
////<td>${status.index + 1}</td>
////<td>${viewRaiseComplaintUsers.complaintId}</td>
////<td>${viewRaiseComplaintUsers.complaintType}</td>
////<td>${viewRaiseComplaintUsers.country}</td>
////<td>${viewRaiseComplaintUsers.state}</td>
////<td>${viewRaiseComplaintUsers.city}</td>
////<td>${viewRaiseComplaintUsers.area}</td>
////<td>${viewRaiseComplaintUsers.address}</td>
////<td>${viewRaiseComplaintUsers.description}</td>
////<td>${viewRaiseComplaintUsers.signUpDTO.id}</td>
////
////
////      <form action="update-department" method="post">
////                   <span style="color:green"><h4>${successMessage}</h4></span>
////
////    <input type="hidden" name="complaintId" value="${viewRaiseComplaintUsers.complaintId}" id="complaintId">
////
////
////
//// <td>
////<select class="form-select status-select" name="departmentId">
////            <option value="Select">Select</option>
////            <c:forEach var="department" items="${departments}">
////                <option value="${department.id}">${department.departmentType}</option>
////            </c:forEach>
////        </select>
////
////</td>
////                     <!--  <select class="form-select status-select ">
////                   <option value="Select">Select</option>
////
////                          <c:forEach var="department" items="${departments}">
////                              <option value="${department.departmentType}">${department.departmentType}</option>
////                               </c:forEach>
////                   </select>--!>
////
////
//// <td>
////        <select class="form-select status-select" name="status">
////            <option value="Select">Select</option>
////            <option value="Pending">Pending</option>
////            <option value="In Process">In Process</option>
////            <option value="Completed">Completed</option>
////        </select>
////    </td>
////
////
//// <td>
////        <button type="submit" class="btn btn-primary">Submit</button>
////</td>
////
////</form>
////
////
////                    </tr>
////                </c:forEach>
////                </tbody>
////            </table>
////        </div>
////    </div>
////</div>
////
////
////
////</body>
////</html>kavyaaa...
//
//
////@PostMapping("/update-department")
////public String updateComplaint(@RequestParam("complaintId") int complaintId,
////                              @RequestParam("departmentId") int departmentId,
////                              @RequestParam("status") String status,
////                              RedirectAttributes redirectAttributes) {
////    adminService.updateStatusAndDepartmentId(complaintId, departmentId, status);
////    redirectAttributes.addFlashAttribute("successMessage", "Department allocated successfully!");
////    return "redirect:/View-raise-complaint";
////}
//
//
//
////@PostMapping("admin-reset-password")
////public String passwordReset(@RequestParam("departmentAdminEmailId")  String email, String oldPassword, String newPassword, String confirmPassword,Model model) {
////    System.out.println("email"+email+"old"+oldPassword+"new"+newPassword+"con"+confirmPassword);
////    boolean resetSuccessful = adminService.changePassword(email, oldPassword, newPassword, confirmPassword);
////    if (resetSuccessful) {
////        System.out.println("Password reset Successful: " + resetSuccessful);
////        model.addAttribute("passwordResetMessage", "Password reset successful");
////        return "DepartmentAdminResetPassword";
////    } else {
////        model.addAttribute("passwordResetError", "Failed to reset password. Please check your password");
////    }
////
////    return "DepartmentAdminResetPassword";
////}
//
//
//
////@Override
////public DepartmentAdminDto findByEmailId(String email) {
////    System.out.println("Running findByEmailId method... ");
////    EntityManager entityManager =entityManagerFactory.createEntityManager();
////    try {
////        Query query =entityManager.createQuery("Select s from DepartmentAdminDto s where s.departmentAdminEmailId=:email");
////        query.setParameter("email",email);
////        DepartmentAdminDto departmentAdminDto= (DepartmentAdminDto) query.getSingleResult();
////        return departmentAdminDto;
////    }
////    catch (Exception e)
////    {
////        e.printStackTrace();
////    }
////    finally {
////        entityManager.close();
////    }
////    return null;
////}
////@Override
////public DepartmentAdminDto findByEmailIdAndPassword(String emailId, String password) {
////    System.out.println("Running findByEmailIdAndPassword method... ");
////    EntityManager entityManager = entityManagerFactory.createEntityManager();
////    try {
////        Query query = entityManager.createQuery("SELECT s FROM DepartmentAdminDto s WHERE s.departmentAdminEmailId = :emailId AND s.departmentAdminPassword = :password");
////        query.setParameter("emailId", emailId);
////        query.setParameter("password", password);
////        DepartmentAdminDto departmentAdminDto = (DepartmentAdminDto) query.getSingleResult();
////        return departmentAdminDto;
////    } catch (NoResultException e) {
////        System.out.println("No result found for email: " + emailId + " with the given password.");
////        return null;
////    } catch (Exception e) {
////        e.printStackTrace();
////        return null;
////    } finally {
////        entityManager.close();
////    }
////}
////
////
////@Override
////public boolean updateDepartmentAdminDetails(DepartmentAdminDto departmentAdminDto) {
////    System.out.println("Running updateDepartmentAdminPassword method AdminRepoImpl...");
////    EntityManager entityManager = entityManagerFactory.createEntityManager();
////    EntityTransaction transaction = entityManager.getTransaction();
////    try {
////        transaction.begin();
////        entityManager.merge(departmentAdminDto);
////        transaction.commit();
////        return true;
////    }
////    catch (PersistenceException e) {
////        if (transaction.isActive()) {
////            transaction.rollback();
////        }
////        e.printStackTrace();
////        return false;
////    }
////    finally {
////        entityManager.close();
////    }
////}
//
//
////@Override
////public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
////    System.out.println("Attempting to change password for email: " + email);
////
////    // Step 1: Check if newPassword matches confirmPassword
////    if (!newPassword.equals(confirmPassword)) {
////        System.out.println("New password and confirm password do not match.");
////        return false;
////    }
////
////    // Step 2: Retrieve departmentAdminDto based on emailId
////    DepartmentAdminDto departmentAdminDto = this.adminRepo.findByEmailId(email);
////    if (departmentAdminDto == null) {
////        System.out.println("User with email " + email + " not found.");
////        return false;
////        // User not found
////    }
////
////    String storedPassword = departmentAdminDto.getDepartmentAdminPassword();
////    System.out.println("Stored password: " + storedPassword);
//
//    // Step 3: Verify oldPassword matches the stored password
////    if (!passwordEncoder.matches(oldPassword, storedPassword)) {
////        System.out.println("Old password verification failed for email: " + email);
////        return false; // Old password doesn't match
////    }
////
////    // Step 4: Encode and update the new password in SignupDto
////    String encodedNewPassword = passwordEncoder.encode(newPassword);
////    departmentAdminDto.setDepartmentAdminPassword(encodedNewPassword);
////
////    // Step 5: Save the updated password in the repository
////    boolean save = adminRepo.updateDepartmentAdminDetails(departmentAdminDto);
////
////    // Step 6: Send email notification if password update was successful
////    if (save) {
////        System.out.println("Password updated successfully for email: " + email);
////        try {
////            mailSending.sendAdminResetPassword(departmentAdminDto, newPassword);
////            return true; // Password successfully updated and email sent
////        } catch (MailException e) {
////            // Handle exception if email sending fails (log it or take appropriate action)
////            e.printStackTrace();
////            return false; // Indicate failure if email sending failed
////        }
////    }
////
////    return false; // Password update failed
////}
//
//
////@PostMapping("generateOtp")
////public String generateOtp(@RequestParam("emailId") String emailId,
////                          @RequestParam("captcha") String captchaInput,
////                          HttpSession httpSession,
////                          Model model,
////                          RedirectAttributes redirectAttributes) {
////    log.info("generateOtp method running in EmployeeController..");
////
////    // Retrieve the CAPTCHA value stored in the session
////    String sessionCaptcha = (String) httpSession.getAttribute("captcha");
////
////    // Validate CAPTCHA
////    if (sessionCaptcha != null && sessionCaptcha.equals(captchaInput)) {
////        // CAPTCHA is valid, proceed with OTP generation
////
////        // Check if the email exists in the database
////        EmployeeDTO employeeDTO = employeeService.findByEmail(emailId);
////        if (employeeDTO != null) {
////            // Generate OTP
////            String otp = emailOTPGenerator.generateOtp();
////            System.out.println("OTP : " + otp);
////
////            // Set the OTP in the employeeDTO
////            employeeDTO.setOtp(Long.parseLong(otp));
////
////            // Save the updated employee data with the OTP
////            boolean isSaved = employeeService.saveEmployeeData(employeeDTO);
////
////            if (isSaved) {
////                // Send OTP to the user's email
////                otpMailSend.sendOtpEmail(employeeDTO.getEmailId(), otp);
////
////                log.info("OTP generated and sent to email: {}", emailId);
////                redirectAttributes.addFlashAttribute("generatedOTP", "OTP generated and sent to email");
////
////                // Redirect to the OTP page
////                return "redirect:/employee-OTP-page";
////            } else {
////                redirectAttributes.addFlashAttribute("failed", "Failed to generate OTP, please try again.");
////                log.error("Failed to save OTP for email: {}", emailId);
////            }
////        } else {
////            redirectAttributes.addFlashAttribute("emailNotFound", "Email not found in the database");
////            log.error("Email not found in the database: {}", emailId);
////        }
////    } else {
////        // CAPTCHA is invalid, return an error message
////        redirectAttributes.addFlashAttribute("error", "Invalid CAPTCHA");
////        log.error("Invalid CAPTCHA for email: {}", emailId);
////    }
////
////    return "redirect:/EmployeeLoginPage.jsp"; // Redirect to the login page on failure
////}
//
//
//
//<script>
//        // Set the countdown time in seconds (5 minutes = 300 seconds)
//        var countdownTime = 300;  // 5 minutes in seconds
//
//function startCountdown() {
//    var timerElement = document.getElementById("timer");
//    var otpField = document.getElementById("otp");
//    var submitButton = document.querySelector("input[type='submit']");
//
//    var countdownInterval = setInterval(function () {
//        var minutes = Math.floor(countdownTime / 60);
//        var seconds = countdownTime % 60;
//
//        // Add leading zeros to seconds
//        seconds = seconds < 10 ? '0' + seconds : seconds;
//
//        // Display the countdown timer
//        timerElement.textContent = minutes + ":" + seconds;
//
//        if (countdownTime <= 0) {
//            clearInterval(countdownInterval);
//            timerElement.textContent = "Time's up!";
//            otpField.disabled = true; // Disable OTP field
//            submitButton.disabled = true; // Disable submit button
//        }
//
//        countdownTime--;
//    }, 1000);
//}
//
//// Start the countdown when the page loads
//window.onload = startCountdown;
//</script>
