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

    List<DepartmentDTO> findAll(String departmentType);

    //update status and department id

    void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


    //register department admin data save

    boolean saveDepartmentAdminData(RegisterDepartmentAdminDTO registerDepartmentAdminDTO);


    //subAdmin login id email exists in database
    RegisterDepartmentAdminDTO findEmailAndPassword(String email, String password,String departmentName);


    //to check email is their or not
    RegisterDepartmentAdminDTO findByEmail(String email);
    //RegisterDepartmentAdminDTO findByEmail(String email); // Add this method to find a user by email


    //forgot password

    public RegisterDepartmentAdminDTO resetPasswordEmail(String email);

    //then I have to update forgot password in database

    void updatePassword(String email, String password);

    //update account locked and attempt failed

    boolean update(RegisterDepartmentAdminDTO  registerDepartmentAdminDTO);


    //************************************************
    //Sub Admin change password


    RegisterDepartmentAdminDTO emailExists(String email);

    RegisterDepartmentAdminDTO verifyOldPassword(String email, String oldPassword);

    boolean departmentAdminUpdatePassword(String email, String newPassword);


    //to add departId in department admin table

    DepartmentDTO findDepartmentByName(String departmentName);


    //to get all departments in jsp

    public List<DepartmentDTO> getAllDepartments();


    //admin can view particular department raise complaint details

    List<RaiseComplaintDTO> getParticularDepartments(String complaintType);

    }

