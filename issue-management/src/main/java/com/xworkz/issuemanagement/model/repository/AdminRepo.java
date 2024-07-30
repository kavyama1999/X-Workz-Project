package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.*;

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
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);


    //find all department

    List<DepartmentDTO> findAll(String  departmentType);

    //update status and department id

    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


    //register department admin data save

    boolean saveDepartmentAdminData(RegisterDepartmentAdminDTO registerDepartmentAdminDTO);

}

