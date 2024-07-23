package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailsending.MailSend;
import com.xworkz.issuemanagement.model.repository.MailRepo;
import com.xworkz.issuemanagement.model.repository.ChangePasswordRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private ChangePasswordRepo changePasswordRepo;

    @Autowired
    private MailRepo mailRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSend mailSend;

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("Trying to change password for email : " + email);

        // Step 1: Check if newPassword matches confirmPassword
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New password and confirm password do not match.");
            return false;
        }

        // Step 2: Retrieve SignUpDto based on email
        SignUpDTO signUpDTO = this.mailRepo.findByEmail(email);
        if (signUpDTO == null) {
            System.out.println("User with email " + email + " not found.");
            return false; // User not found
        }

        String storedPassword = signUpDTO.getPassword();
        System.out.println("Stored password: " + storedPassword); // Encoded password

        // Step 3: Verify oldPassword matches the stored password
        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            System.out.println("Old password verification failed for email: " + email);
            return false; // Old password doesn't match
        }
                 ////123456
        // Step 4: Save the updated password in the repository (database)
        // Step 5: Encode and update the new password in SignUpDTO
        signUpDTO.setPassword(passwordEncoder.encode(newPassword)); ///qwdasflkjaartsdhf
        boolean saveEmail = changePasswordRepo.updatePassword(email, signUpDTO.getPassword());
        if (saveEmail) {
            System.out.println("Password updated successfully for email: " + email);
            // Step 4: Send the plain text new password in the email before encoding it
            try {
                // Step 5: Encode and update the new password in SignUpDTO  //123456
               // signUpDTO.setPassword(newPassword);  //123456
                mailSend.sendChangePassword(signUpDTO, newPassword); // Send plain text new password in email
            } catch (MailException e) {
                // Handle exception if email sending fails (log it or take appropriate action)
                e.printStackTrace();
                return false; // Indicate failure if email sending failed
            }
            return true; // Password successfully updated
        }
        return false; // Password update failed
    }
}
