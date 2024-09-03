package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {


    //to save employee data in database
    boolean saveEmployeeData(EmployeeDTO employeeDTO);


    //to fetch employeeName from employee table


    List<String> fetchEmployeeName();

//to check whether emailId exists or not in database

    EmployeeDTO findByEmail(String emailId);


//when i select allocate employeeName that id should saved to save in Complaint raise table

    void updateEmployeeId(int complaintId, int employeeId);

//when i select status

    public void updateEmployeeStatus(int employeeId, String status);


}