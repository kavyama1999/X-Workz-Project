package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.MailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailRepo mailRepo;

    @Override
    public void sendPasswordEmail(String toEmail, String subject, String body) {


        //to send password to email

//it is class of spring frameWork
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("kmsrcb@gmail.com");

        javaMailSender.send(message);

    }

    //sign in page we need

    @Override
    public SignUpDTO findByEmailAndPassword(String email, String password) {
        SignUpDTO user = mailRepo.findByEmail(email);

        if (user != null && !user.isAccountLocked() && passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Data is present: " + user);
            return user;
        }

        System.out.println("Data is not present: " + email);
        return null;
    }


    //for sign in
//    @Override
//    public SignUpDTO findByEmailAndPassword(String email, String password) {
//        SignUpDTO user = mailRepo.findByEmailAndPassword(email, password);
//        SignUpDTO signUpDTO =  mailRepo.findByEmail(email);
//
//
//        if (user != null && !user.isAccountLocked() && passwordEncoder.matches(password,user.getPassword())) {
//            System.out.println("Data is present"+signUpDTO);
//            //passwordEncoder.matches(password, signUpDTO.getPassword());
//            // if (signUpDTO != null && !signUpDTO.isAccountLocked() && passwordEncoder.matches(password,signUpDTO.getPassword())) {
//
//            return user;
//        }
//        System.out.println("Data is not present "+signUpDTO);
//        return null;
//    }


    //pruthvi
    @Override
    public void incrementFailedAttempts(String email) {

        SignUpDTO user = mailRepo.findByEmail(email);
        if (user != null) {
            int attempts = user.getFailedAttempt() + 1;
            user.setFailedAttempt(attempts);
            if (attempts >= 3) {
                user.setAccountLocked(true);
            }
            mailRepo.update(user);
        }

    }

    @Override
    public int getFailedAttempts(String email) {
        SignUpDTO user = mailRepo.findByEmail(email);
        return (user != null) ? user.getFailedAttempt() : 0;
    }


    @Override
    public void resetFailedAttempts(String email) {

        SignUpDTO user = mailRepo.findByEmail(email);
        if (user != null) {
            user.setFailedAttempt(0); //false
            mailRepo.update(user);


        }
    }


    @Override
    public void lockAccount(String email) {
        SignUpDTO user = mailRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(true);
            mailRepo.update(user);
        }
    }


    //to unlock account when i new password generate
    @Override
    public void unlockAccount(String email) {
        SignUpDTO user = mailRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(false);
            mailRepo.update(user);
        }
    }
}

