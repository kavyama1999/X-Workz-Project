package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeRepo {



//to save employee data
    boolean saveEmployeeData(EmployeeDTO employeeDTO);


    //fetch all emplyee name


   List<String> fetchEmployeeName();


    //to check whether email exists or not in database

    EmployeeDTO findByEmail(String  emailId);
}
