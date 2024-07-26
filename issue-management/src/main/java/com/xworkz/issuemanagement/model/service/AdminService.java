package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.ComplaintDepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminService {

    public boolean findByEmailAndPassword(String email, String password);


    //admin can view all user details(signUp details)

    List<SignUpDTO> findById(SignUpDTO signUpDTO);

//admin can view all user raise Complaint details(RaiseComplaintDTO)

    List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO);



    //admin search by based on complaint type And city

    List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType,String city);


    //admin search by complaint type or city

     List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city);

     //save department

    ComplaintDepartmentDTO saveDepartment(ComplaintDepartmentDTO complaintDepartmentDTO);

}


//public List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
//    // Implement the search logic here
//}
//
//public List<RaiseComplaintDTO> searchByComplaintType(String complaintType) {
//    // Implement the search logic here
//}
//
//public List<RaiseComplaintDTO> searchComplaintByCity(String city) {
//    // Implement the search logic here
//}
