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


    //when i select allocate employeeName that id should saved to save in Complaint raise table

    void updateEmployeeId(int complaintId,int employeeId);

    //when i select status that should

    void updateEmployeeStatus(int employeeId,String status);

}
//void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);

