package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface MailService {

    //to send password to email

    public void sendPasswordEmail(String toEmail, String subject, String body);


    //sign in page we need
    SignUpDTO findByEmailAndPassword(String email, String password);


    //checking wrong password and lock the account

    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);


    //to unlock when I new password generate
    void unlockAccount(String email);

}






