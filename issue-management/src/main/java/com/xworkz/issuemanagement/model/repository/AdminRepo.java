package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminRepo {



    public AdminDTO findByEmailAndPassword(String email,String password);


    //Admin can view all user data
    List<SignUpDTO> findById(SignUpDTO signUpDTO);
}
