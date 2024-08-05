package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.RegisterDepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email, String password);


    //admin can view all user details(signUp details)

    List<SignUpDTO> findById(SignUpDTO signUpDTO);

//admin can view all user raise Complaint details(RaiseComplaintDTO)

    List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO);


    //admin search by based on complaint type And city

    List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city);


    //admin search by complaint type or city

    List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city);

    //save department

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);


    //find all Department

     List<DepartmentDTO> findAll(String departmentType) ;


//update status and department id

    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


    //save department admin data

     boolean saveDepartmentAdminData(RegisterDepartmentAdminDTO registerDepartmentAdminDTO);


    //subAdmin login id email exists in database

    public RegisterDepartmentAdminDTO findEmailAndPassword(String email, String password);


    //to reset password

    public RegisterDepartmentAdminDTO resetPasswordEmail(String email);



    //checking wrong password and lock the account

    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);


    //to unlock when I new password generate
    void unlockAccount(String email);

    //to update
    //to send password to email
   //public void sendPasswordEmail(String toEmail, String subject, String body);

}


