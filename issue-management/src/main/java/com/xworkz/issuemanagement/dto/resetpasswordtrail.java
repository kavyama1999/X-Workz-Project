////
////
//////here we can use  name attributes for ...validation we can use id
////
////let fieldsChecks = {
////        "oldPassword": false,
////        "newPassword": false,
////        "confirmPassword": false
////        };
////
////function validateAndEnableSubmit() {
////    let flag = false;
////
////    for (let value of Object.values(fieldsChecks)) {
////        if (!value) {
////            flag = true;
////            break;
////        }
////    }
////
////    if (!flag) {
////        document.getElementById("submit").removeAttribute("disabled");
////    } else {
////        document.getElementById("submit").setAttribute("disabled", "");
////    }
////}
////
////
////
//////function emailValidation() {
//////    let element = document.getElementById("email");
//////    let error = document.getElementById("emailError");
//////
//////    // Regular expression pattern for validating email address
//////    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//////
//////    // Check if the email is valid
//////    if (emailRegex.test(element.value)) {
//////        // Email is valid
//////        error.innerHTML = "";
//////        fieldsChecks["email"] = true;
//////    } else {
//////        // Email is invalid
//////        error.innerHTML = "Invalid email address.";
//////        error.style.color = "red";
//////        fieldsChecks["email"] = false;
//////    }
//////
//////    validateAndEnableSubmit();
//////}
////
////
//////old password
////
////function oldPasswordValidation() {
////    let oldPassword = document.getElementById("oldPassword");
////    let error = document.getElementById("oldPasswordError");
////
////    // Regular expression pattern for validating the password
//////    let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{10}$/;
////
////    let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10}$/;
////
////
////    // Check if the password is valid
////    if (oldPasswordRegex.test(oldPassword.value)) {
////        // Password is valid
////        error.innerHTML = "";
////        fieldsChecks["oldPassword"] = true;
////    } else {
////        // Password is invalid
////        error.innerHTML = "Password must be exactly 10 characters long, with at least one uppercase letter, one lowercase letter, one number,and one special character";
////        error.style.color = "red";
////        fieldsChecks["oldPassword"] = false;
////    }
////
////    validateAndEnableSubmit();
////}
////
////
////
////
////
////
////
//////new Password
////
////function confirmPasswordValidation() {
////    let newPassword = document.getElementById("newPassword");
////    let error = document.getElementById("newPasswordError");
////
////    // Regular expression pattern for validating the password
//////    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{10}$/;
////
////    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10}$/;
////
////
////    // Check if the password is valid
////    if (newPasswordRegex.test(newPassword.value)) {
////        // Password is valid
////        error.innerHTML = "";
////        fieldsChecks["newPassword"] = true;
////    } else {
////        // Password is invalid
////        error.innerHTML = "Password must be exactly 10 characters long, with at least one uppercase letter, one lowercase letter,  one number,and one special character.";
////        error.style.color = "red";
////        fieldsChecks["newPassword"] = false;
////    }
////
////    // Validate confirm password to ensure consistency
////    confirmPasswordValidation();
////
////    validateAndEnableSubmit();
////}
////
////
//////confirm validation
////function confirmPasswordValidation() {
////    let newPassword = document.getElementById("newPassword");
////    let confirmPassword = document.getElementById("confirmPassword");
////    let error = document.getElementById("confirmPasswordError");
////
////    // Regular expression pattern for validating the password
//////    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{10}$/;
////
////    let confirmPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10}$/;
////
////
////    // Check if the password is valid
////    if (!confirmPasswordRegex.test(confirmPassword.value)) {
////        // Password is invalid
////        error.innerHTML = "Password must be exactly 10 characters long, with at least one uppercase letter, one lowercase letter,  one number,and one special character.";
////        error.style.color = "red";
////        fieldsChecks["confirmPassword"] = false;
////    } else if (confirmPassword.value !== newPassword.value) {
////        // Passwords do not match
////        error.innerHTML = "Passwords do not match.";
////        error.style.color = "red";
////        fieldsChecks["confirmPassword"] = false;
////    } else {
////        // Password is valid and matches
////        error.innerHTML = "";
////        fieldsChecks["confirmPassword"] = true;
////    }
////
////    validateAndEnableSubmit();
////}
////
////
///
////
//////function oldPasswordValidation() {
//////    let oldPassword = document.getElementById("oldPassword");
//////    let error = document.getElementById("oldPasswordError");
//////
//////    // Regular expression pattern for validating the password
//////    let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{10}$/;
//////
//////    // Check if the password does not match the regular expression pattern
//////    if (!passwordRegex.test(oldPassword.value)) {
//////        // Password is invalid
//////        error.innerHTML = "Password must be exactly 10 characters long, with at least one uppercase letter, one lowercase letter, and one number.";
//////        error.style.color = "red";
//////        fieldsChecks["password"] = false;
//////    } else {
//////        // Password is valid
//////        error.innerHTML = "";
//////        fieldsChecks["password"] = true;
//////    }
//////
//////    validateAndEnableSubmit();
//////}
////
////
////
////
////
////
////
////
//////function validateAndEnableSubmit() {
//////    // Example validation logic to enable the submit button if all fields are valid
//////    let submitButton = document.getElementById("submitButton");
//////    if (fieldsChecks["newPassword"] && fieldsChecks["confirmPassword"]) {
//////        submitButton.disabled = false;
//////    } else {
//////        submitButton.disabled = true;
//////    }
//////}
//////
//////    // Example object to track the validation status of fields
//////   let fieldsChecks = {
//////    newPassword: false,
//////    confirmPassword: false
//////};
////
////
//////function validatePassword() {
//////    let password = document.getElementById("password").value;
//////    let error = document.getElementById("passwordError");
//////    let regex = /^(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-])(?=.*\d)[A-Za-z\d!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]{16,}$/;
//////
//////    if (!regex.test(password)) {
//////        error.innerHTML = "Password must be at least 16 characters long and contain at least one capital letter, one special character, and one number.";
//////        getFields["password"] = false;
//////    } else {
//////        error.innerHTML = "";
//////        getFields["password"] = true;
//////    }
//////    validate();
//////}
////
//
//
//
//
//
////////////////////////////////password controller
//
//
//   //    // package com.xworkz.issuemanagement.controller;
//
//
////import com.xworkz.issuemanagement.model.service.PasswordResetService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////
////@Controller
////@RequestMapping("/")
////public class PasswordResetController {
////
////
////    @Autowired
////    private PasswordResetService passwordResetService;
////
////
////    public PasswordResetController() {
////        System.out.println("No param constructor created for PasswordResetController...");
////    }
////
////
////    @PostMapping("reset-password")
////    public String passwordReset(Model model, String email, String oldPassword, String newPassword, String confirmPassword) {
////
////        boolean resetSuccessful = passwordResetService.resetPassword(email, oldPassword, newPassword, confirmPassword);
////        if (resetSuccessful)
////        {
////            System.out.println("Password reset Successful:"+resetSuccessful);
////            model.addAttribute("passwordResetMessage", "Password reset successful");
////
////        }
////        else
////        {
////            model.addAttribute("passwordResetError", "Failed to reset password.Please check your password");
////        }
////
////        return "PasswordReset";
////    }
////}
