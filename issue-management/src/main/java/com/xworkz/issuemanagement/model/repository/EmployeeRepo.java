package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EmployeeDTO;

public interface EmployeeRepo {



//to save employee data
    boolean saveEmployeeData(EmployeeDTO employeeDTO);


    //to check whether email exists or not in database

   // EmployeeDTO findByEmail(String  emailId);
}
