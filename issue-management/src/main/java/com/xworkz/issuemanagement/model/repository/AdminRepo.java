package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.ComplaintDepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminRepo {


    public AdminDTO findByEmailAndPassword(String email, String password);


    //Admin can view all user data
    List<SignUpDTO> findById(SignUpDTO signUpDTO);

    //Admin can view All user Raised Complaint details

    List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO);


    //search by complaint type

    List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city);


    //search by complaintType and city

    List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city);

    //save department
    ComplaintDepartmentDTO saveDepartment(ComplaintDepartmentDTO complaintDepartmentDTO);

}

