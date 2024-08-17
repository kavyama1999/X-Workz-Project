package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;

public interface EmployeeService {


    //to save employee data in database
    boolean saveEmployeeData(EmployeeDTO employeeDTO);


//to check whether emailId exists or not in database

    //EmployeeDTO findByEmail(String  emailId);




}
