package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email,String password);


    //admin can view all user details(signUp details)

    List<SignUpDTO> findById(SignUpDTO signUpDTO);
}
