package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignUpRepo {

    //save data to database

    public boolean userDataSave(SignUpDTO signUpDTO);




}
