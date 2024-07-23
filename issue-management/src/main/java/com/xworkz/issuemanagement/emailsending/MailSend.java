package com.xworkz.issuemanagement.emailsending;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSend {


    @Autowired
    private JavaMailSender javaMailSender;

    public MailSend() {
        log.info("No parameters in MailSend..");
    }


    public void sendPassword(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("SignIn password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", You have been successfully Signed Up,\n\n" +
                "Please Sign in through this password: " + signUpDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }


    public void forgotPassword(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Forgot password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", A new password has been sent to your email. ,\n\n" +
                "Please Sign in through new password: " + signUpDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }

    public void sendChangePassword(SignUpDTO signUpDTO,String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Change password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", A Change Password has been sent to your email. ,\n\n" +
                "Please Sign in through Change password: " + newPassword+ "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }
}
