package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.emailsending.OTPMailSend;
import com.xworkz.issuemanagement.model.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired  //to get the reference class
    private EmployeeRepo employeeRepo;


    @Autowired
    private OTPMailSend otpMailSend; // Assuming you have a service to send emails


    @Override
    public boolean saveEmployeeData(EmployeeDTO employeeDTO) {

        log.info("saveEmployeeData method running in EmployeeServiceImpl..");

        boolean employeeData = employeeRepo.saveEmployeeData(employeeDTO);

        if (employeeData) {
            log.info("employeeData saved successfully in EmployeeServiceImpl.. ");
            return true;
        } else {
            log.info("employeeData not saved successfully in EmployeeServiceImpl..");
        }
        return false;
    }

    //*************************************************************
    //to fetch all employee name from employee table
    //here we can take <String> or <EmployeeDTO>
    @Override
    public List<EmployeeDTO> fetchEmployeeNamesByDepartment(String departmentName) {
        log.info("fetchEmployeeName method running in EmployeeServiceImpl..");

        List<EmployeeDTO> fetchEmployeeName = employeeRepo.fetchEmployeeNamesByDepartment(departmentName);

        if (fetchEmployeeName != null) {
            log.info("EmployeeName fetched successfully.. ");
            return fetchEmployeeName;
        } else {
            log.info("EmployeeName not fetched successfully..");
        }

        return fetchEmployeeName;

    }

    //****************************************************************

    //to check whether emailId exists or not in database
    @Override
    public EmployeeDTO findByEmail(String emailId) {

        log.info("findByEmail method running EmployeeServiceImpl..");

        log.info("Your EmailId : {}", "emailId");
        EmployeeDTO employeeDTO = employeeRepo.findByEmail(emailId);

        if (employeeDTO != null) {
            log.info("EmailId exists in database");
            return employeeDTO;
        } else {
            log.info("EmailId not exists in database");
        }


        return null;
    }

    //    @Transactional
    @Override
    public boolean updateEmployeeForComplaint(int employeeId, int complaintId) {
        boolean data = employeeRepo.updateEmployeeForComplaint(employeeId, complaintId);
        return data;
    }


    //****************************************
    //department admin delete allocated employee name

    @Override
    //@Transactional
    public boolean deleteAllocatedEmployee(int employeeId, int complaintId) {

        // Delete allocated employee from complaint_raise
      // boolean deleteSuccess = employeeRepo.deleteAllocatedEmployee(employeeName);

        // Update the employee status to 'inactive'
        boolean updateSuccess = employeeRepo.updateEmployeeStatusToInActive(employeeId,complaintId);

        // If both operations succeed, return true
        return updateSuccess;
    }
    //return false;
}


//************************************************************
//when i select allocate employeeName that id should saved to save in Complaint raise table









