package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;

import java.util.List;

public interface EmployeeService {


    //to save employee data in database
    boolean saveEmployeeData(EmployeeDTO employeeDTO);


    //to fetch employeeName from employee table


    //    List<String> fetchEmployeeName();
    List<EmployeeDTO> fetchEmployeeNamesByDepartment(String departmentName);


//to check whether emailId exists or not in database

    EmployeeDTO findByEmail(String emailId);


//when i select allocate employeeName that id should saved to save in Complaint raise table

    boolean updateEmployeeForComplaint(int employeeId,int complaintId);

//delete allocated employee

    boolean deleteAllocatedEmployee(int employeeId,int complaintId);

}