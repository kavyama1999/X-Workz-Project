package com.xworkz.issuesmanagement.model.repository;

import com.xworkz.issuesmanagement.dto.SignUpDTO;

public interface SignUpRepo {


    public boolean userDataSave(SignUpDTO signUpDTO);


    //to generate password

  public SignUpDTO findByEmailAndPassword(String email, String password);



    //Pruthvi
    SignUpDTO findByEmail(String email); // Add this method to find a user by email


    boolean update(SignUpDTO signUpDto);


    //duplicate

    SignUpDTO findByExistsEmail(String email);


}


