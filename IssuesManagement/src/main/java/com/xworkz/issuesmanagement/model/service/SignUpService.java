package com.xworkz.issuesmanagement.model.service;

import com.xworkz.issuesmanagement.dto.SignUpDTO;

import java.time.LocalDateTime;

public interface SignUpService {

    public boolean saveAndValidate(SignUpDTO  signUpDTO);


    //set
    public void setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive);


    //to call generate password in service ..so we have to write 1 method
    SignUpDTO findByEmailAndPassword(String email, String password);


    public String generateRandomPassword();


//to send password to email

    public void sendPasswordEmail(String toEmail, String subject, String body);


    //checking wrong password and lock the account

    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    //for duplicate email
    SignUpDTO findByExistsEmail(String email);

}
