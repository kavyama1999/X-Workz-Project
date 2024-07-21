package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailsending.MailSend;
import com.xworkz.issuemanagement.model.repository.ForgotPasswordRepo;
import com.xworkz.issuemanagement.util.EmailPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepo forgotPasswordRepo;

    @Autowired
    private JavaMailSender javaMailSender;  //MailConfiguration


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private MailSend mailSend;

    @Autowired
    private MailService mailService; //

    @Override
    public boolean resetPassword(String email) {
        System.out.println("resetPassword method running in ResetPasswordServiceImpl");

        SignUpDTO user = forgotPasswordRepo.findByEmail(email);
        if (user != null) {
            String newPassword = EmailPasswordGenerator.generatePassword();
//            forgotPasswordRepo.updatePassword(email, newPassword);

            forgotPasswordRepo.updatePassword(email, passwordEncoder.encode(newPassword));
            user.setPassword(newPassword);

            mailSend.forgotPassword(user);



            // Reset failed attempts
            mailService.resetFailedAttempts(email);
            mailService.unlockAccount(email);


//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(email);
//            message.setSubject("Password Reset");
//            message.setText("Your new password is: " + newPassword);
//           // javaMailSender.send(message);
//            mailSend.sendPassword();


            return true;
        }

        else
        {
            System.out.println("data not exists");
        }
        return false;
    }
}
