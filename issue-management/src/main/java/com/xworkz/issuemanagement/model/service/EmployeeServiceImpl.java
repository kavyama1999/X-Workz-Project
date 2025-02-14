package com.xworkz.issuemanagement.model.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.emailsending.EmailServiceUser;
import com.xworkz.issuemanagement.emailsending.OTPMailSend;
import com.xworkz.issuemanagement.model.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired  //to get the reference class
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmailServiceUser emailServiceUser;


    @Autowired
    private OTPMailSend otpMailSend; // Assuming you have a service to send emails


    @Override
    public boolean saveEmployeeData(EmployeeDTO employeeDTO) {
        System.out.println("kavya-----:" + employeeDTO);


//        try {
//            String emp = new ObjectMapper().writeValueAsString(employeeDTO);  //it will print  data in json format
//            System.out.println("Kavya... : " + emp);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        //-----
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
        boolean updateSuccess = employeeRepo.updateEmployeeStatusToInActive(employeeId, complaintId);

        // If both operations succeed, return true
        return updateSuccess;
    }


    //employee can view particular department details

    @Override
    public List<RaiseComplaintDTO> getParticularDepartments(String emailId) {

        log.info("Employee can view particular department....");
        List<RaiseComplaintDTO> getParticularDepartment = employeeRepo.getParticularDepartments(emailId);
        log.info("RaiseComplaintDTO :{}", getParticularDepartment);

        if (!getParticularDepartment.isEmpty()) {
            log.info("RaiseComplaintDTO data successful in AdminServiceImpl");
            return getParticularDepartment;
        } else {
            log.info("RaiseComplaintDTO data not successful in AdminServiceImpl");

        }
        return Collections.emptyList();
    }

    @Override
    public void updateStatusRaiseComplaintAndNotifyUser(int complaintId, String complaintStatus) {
        log.info("updateStatusInComplaintRaiseTable method in EmployeeServiceImpl");

        String userEmail = employeeRepo.updateStatusRaiseComplaintAndNotifyUser(complaintId, complaintStatus);

        // Send an email notification if the status is "Completed"
//        if (userEmail != null && "Completed".equalsIgnoreCase(complaintStatus)) {
//            String subject = "Your Complaint Has Been Resolved";
//            String message = "Dear user,\n\nYour complaint (ID: " + complaintId + ") has been resolved successfully. Please provide your feedback.";
//            emailServiceUser.sendEmail(userEmail, subject, message); // Assuming an email service exists
//            log.info("Email sent to user: {}", userEmail);
//        }


        if (userEmail != null && "Completed".equalsIgnoreCase(complaintStatus)) {
            //String feedbackFormLink = "http://localhost:8080/submit-feedback?complaintId=" + complaintId;
            String subject = "Your Complaint Has Been Resolved";

           // String serverIp = "192.168.33.1"; // Replace with your machine's IP address


//            String feedbackFormLink = "http://localhost:8080/submit-feedback-page?complaintId=" + complaintId;

            String feedbackFormLink = "http://localhost:8082/submit-feedback-page?complaintId=" + complaintId;

            String message = "Dear user,\n\nYour complaint (ID: " + complaintId + ") has been resolved successfully.\n\n"
                    + "Please provide your feedback by clicking the link below:\n" + feedbackFormLink + "\n\nThank you!";
            emailServiceUser.sendEmail(userEmail, subject, message);
            log.info("Feedback email sent to user: {}", userEmail);
        }

    }

    @Override
    public void updateUserFeedback(int complaintId, String feedbackText) {
        log.info("updateFeedback method running in EmployeeServiceImpl");
        employeeRepo.updateUserFeedback(complaintId, feedbackText);

    }

}


//************************************************************
//when i select allocate employeeName that id should saved to save in Complaint raise table









