package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email,String password);


    //admin can view all user details(signUp details)

    List<SignUpDTO> findById(SignUpDTO signUpDTO);

//admin can view all user raise Complaint details(RaiseComplaintDTO)

    List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO);

    //admin search by based on complaint type

    List<RaiseComplaintDTO> searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO) ;

    }
