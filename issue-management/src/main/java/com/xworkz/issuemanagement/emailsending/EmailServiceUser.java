package com.xworkz.issuemanagement.emailsending;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceUser {

    @Autowired
        private JavaMailSender javaMailSender;

        public void sendEmail(String to, String subject, String text) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);
                javaMailSender.send(message);
                log.info("Email sent to: {}", to);
            } catch (Exception e) {
                log.error("Failed to send email", e);
            }
        }
    }


