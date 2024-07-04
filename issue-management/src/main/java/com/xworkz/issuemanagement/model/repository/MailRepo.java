package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface MailRepo {


    //to send password to email
    public SignUpDTO findByEmailAndPassword(String email, String password);



    //Pruthvi
    SignUpDTO findByEmail(String email); // Add this method to find a user by email


    boolean update(SignUpDTO signUpDto);
}
