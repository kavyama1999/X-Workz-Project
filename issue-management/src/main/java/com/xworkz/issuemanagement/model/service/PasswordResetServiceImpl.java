package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.PasswordResetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {


    @Autowired
    private PasswordResetRepo passwordResetRepo;


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        if (!passwordResetRepo.emailExists(email))
        {

            return false;
        }

        if (!passwordResetRepo.verifyOldPassword(email, oldPassword))
        {
            return false;
        }
        if (!newPassword.equals(confirmPassword))
        {
            return false;
        }
        passwordResetRepo.updatePassword(email, newPassword);
        sendPasswordEmail(email, "Password Reset Successful", "Your password has been successfully reset..Your new password is :" +newPassword);

        return true;
    }

    @Override
    public void sendPasswordEmail(String toEmail, String subject, String body) {


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("kmsrcb@gmail.com");


        javaMailSender.send(simpleMailMessage);
    }
}
