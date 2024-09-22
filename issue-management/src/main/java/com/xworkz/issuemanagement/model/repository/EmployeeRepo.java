package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;

import java.util.List;

public interface EmployeeRepo {



//to save employee data
    boolean saveEmployeeData(EmployeeDTO employeeDTO);


    //fetch all emplyee name


//   List<String> fetchEmployeeName();
 List<EmployeeDTO > fetchEmployeeNamesByDepartment(String departmentName);



    //to check whether email exists or not in database

    EmployeeDTO findByEmail(String  emailId);


    //when i select allocate employeeName that id should saved to save in Complaint raise table


        boolean updateEmployeeForComplaint( int employeeId, int complaintId);

//department admin delete allocated employee

  //boolean deleteAllocatedEmployee(String employeeName);


    //update  delete status(inActive) in employeeDTO
     boolean  updateEmployeeStatusToInActive(int employeeId,int complaintId) ;


    }
//void updateStatusAndDepartmentId(int complaintId, int departmentId, String status);

