package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.emailsending.AdminMailSend;
import com.xworkz.issuemanagement.model.repository.AdminRepo;
import com.xworkz.issuemanagement.util.EmailPasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AdminMailSend adminMailSend;

    @Override
    public boolean findByEmailAndPassword(String email, String password) {

        System.out.println("findByEmailAndPassword method in Service Implementation");

        AdminDTO data = adminRepo.findByEmailAndPassword(email, password);

        if (data != null) {
            System.out.println("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDTO> findById(SignUpDTO signUpDTO) {

        System.out.println("findById method in AdminServiceImpl..");
        List<SignUpDTO> dtoData = adminRepo.findById(signUpDTO);
        if (dtoData != null) {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO) {
        System.out.println("findById method in AdminServiceImpl(Raise complaintDTO)");

        List<RaiseComplaintDTO> data = adminRepo.findById(raiseComplaintDTO);
        if (data != null) {
            System.out.println("findById  data successful in AdminServiceImpl");
            return data;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl");
        }
        return Collections.emptyList();
    }


    //type AND city

    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeORCity method running in AdminServiceImpl..");
        List<RaiseComplaintDTO> data = adminRepo.searchByComplaintTypeAndCity(complaintType, city);

        if (!data.isEmpty()) {
            System.out.println("searchByComplaintTypeORCity successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("searchByComplaintTypeORCity not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }


    //search by complaint type OR city
    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl..");

        List<RaiseComplaintDTO> listOfData = adminRepo.searchByComplaintTypeOrCity(complaintType, city);
        if (!listOfData.isEmpty()) {
            System.out.println("searchComplaintTypeAndCity successful in AdminServiceImpl");
            return listOfData;
        } else {
            System.out.println("searchByComplaintTypeAndCity not successful in AdminServiceImpl..");
        }

        return Collections.emptyList();
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        System.out.println("saveDepartment method running in AdminServiceImpl..");

        DepartmentDTO data = adminRepo.saveDepartment(departmentDTO);

        System.out.println("data:" + data);

        if (data != null) {
            System.out.println("saveDepartment  successful in AdminServiceImpl..");

            return data;
        } else {
            System.out.println("saveDepartment not successful in AdminServiceImpl..");
        }

        return null;
    }


    //find all department

    @Override
    public List<DepartmentDTO> findAll(String departmentType) {
        System.out.println("findAll method running in AdminServiceImpl..");
        List<DepartmentDTO> data = adminRepo.findAll(departmentType);
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl.. ");

        if (data != null) {
            System.out.println("findAll successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("findAll  not successful in AdminServiceImpl..");
        }
        return null;
    }


    //update department id and Status

    @Override
    public void updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {

        //update status and department id

        System.out.println("updateStatusAndDepartmentId method running in RaiseComplaintService");
        adminRepo.updateStatusAndDepartmentId(complaintId, departmentId, status);
    }

    @Override
    public boolean saveDepartmentAdminData(RegisterDepartmentAdminDTO registerDepartmentAdminDTO) {
        System.out.println("saveDepartmentAdminData method running in saveDepartmentAdminData..");


        //generating password to stored in  database(encrypt password)

        String generatedPassword = EmailPasswordGenerator.generatePassword();// i have created ine password cls EmailPasswordGenerator

        //we will get decode that i have to make encode and stored in database

        registerDepartmentAdminDTO.setPassword(passwordEncoder.encode(generatedPassword));

        boolean saveData = adminRepo.saveDepartmentAdminData(registerDepartmentAdminDTO);

        if (saveData) {
            System.out.println("saveDepartmentAdminData saved successfully..");

            registerDepartmentAdminDTO.setPassword(generatedPassword);

            //mail send
            adminMailSend.sendPassword(registerDepartmentAdminDTO);
            System.out.println("Your decoded password id : " + generatedPassword);
            return saveData;
        } else {
            System.out.println("saveDepartmentAdminData not saved successfully....");
        }

        return false;
    }


    //******************************************************************//
    //subAdmin login using email and password
    //here we also match department name
    @Override
    public RegisterDepartmentAdminDTO findEmailAndPassword(String email, String password, String departmentName) {
        log.info("findEmailAndPassword method running in AdminServiceImpl..");

        // Retrieve the RegisterDepartmentAdminDTO by email
        RegisterDepartmentAdminDTO registerDepartmentAdminDTO = adminRepo.findByEmail(email);

        // Check if the retrieved object is null
        if (registerDepartmentAdminDTO != null) {
            // Compare passwords and department name
            if (passwordEncoder.matches(password, registerDepartmentAdminDTO.getPassword()) && registerDepartmentAdminDTO.getDepartmentName().equals(departmentName)) {
                log.info("findEmailAndPassword successful in AdminServiceImpl..");
                return registerDepartmentAdminDTO;
            } else {
                log.info("findEmailAndPassword not successful in AdminServiceImpl..");
                return null;
            }
        } else {
            // Log that the email was not found
            log.info("No account found with email: " + email);
            return null;
        }
    }




    //**************************************************************//
    //reset password
    @Override
    public RegisterDepartmentAdminDTO resetPasswordEmail(String email) {
        log.info("resetPasswordEmail method running in AdminServiceImpl..");

        RegisterDepartmentAdminDTO resetEmail = adminRepo.resetPasswordEmail(email);
        if (resetEmail != null) {
            log.info("resetPasswordEmail successful in AdminServiceImpl..");

            //generate random password
            String newPassword = EmailPasswordGenerator.generatePassword();


            adminRepo.updatePassword(email, passwordEncoder.encode(newPassword));
            //set new password
            resetEmail.setPassword(newPassword);

            adminMailSend.forgotPassword(resetEmail);

            // Reset failed attempts
            // adminRepo.resetFailedAttempts(email);
            //mailService.unlockAccount(email);
            //
            return resetEmail;
        } else {
            log.info("Email not exits..");
        }
        return null;
    }


//********************************************************

    //checking wrong password and lock the account when i


    @Override
    public void incrementFailedAttempts(String email) {

        RegisterDepartmentAdminDTO user = adminRepo.findByEmail(email);
        if (user != null) {
            int attempts = user.getFailedAttempt() + 1;
            user.setFailedAttempt(attempts);
            if (attempts >= 3) {
                user.setAccountLocked(true);
            }
            adminRepo.update(user);
        }

    }

    @Override
    public int getFailedAttempts(String email) {
        RegisterDepartmentAdminDTO user = adminRepo.findByEmail(email);
        return (user != null) ? user.getFailedAttempt() : 0;
    }


    @Override
    public void resetFailedAttempts(String email) {

        RegisterDepartmentAdminDTO user = adminRepo.findByEmail(email);
        if (user != null) {
            user.setFailedAttempt(0); //false
            adminRepo.update(user);


        }
    }


    @Override
    public void lockAccount(String email) {
        RegisterDepartmentAdminDTO user = adminRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(true);
            adminRepo.update(user);
        }
    }


//to send password to email

    //@Override
    //public void sendPasswordEmail(String toEmail, String subject, String body) {


    //to send password to email

//it is class of spring frameWork
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//        message.setFrom("kmsrcb@gmail.com");
//
//        javaMailSender.send(message);
//
//    }


    //to unlock account when i new password generate
    @Override
    public void unlockAccount(String email) {
        RegisterDepartmentAdminDTO user = adminRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(false);
            adminRepo.update(user);
        }
    }


    //*****************************************************
    //change password

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("Trying to change password for email : " + email);

        // Step 1: Check if newPassword matches confirmPassword
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New password and confirm password do not match.");
            return false;
        }

        // Step 2: Retrieve RegisterDepartmentAdminDTO based on email

        RegisterDepartmentAdminDTO registerDepartmentAdminDTO = this.adminRepo.findByEmail(email);
        if (registerDepartmentAdminDTO == null) {
            System.out.println("User with email : " + email + "not found..");
            return false;
        }

        String storedPassword = registerDepartmentAdminDTO.getPassword();
        System.out.println("Stored password : " + storedPassword); // Encoded password

        // Step 3: Verify oldPassword matches the stored password
        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            System.out.println("Old password verification failed for email : " + email);
            return false; // Old password doesn't match
        }

        registerDepartmentAdminDTO.setPassword(passwordEncoder.encode(newPassword));
        boolean saveEmail = adminRepo.departmentAdminUpdatePassword(email, registerDepartmentAdminDTO.getPassword());

        if (saveEmail) {
            System.out.println("Password updated successfully for email: " + email);


            // Step 4: Send the plain text new password in the email before encoding it
            try {
                adminMailSend.sendChangePassword(registerDepartmentAdminDTO, newPassword); // Send plain text new password in email

                return  true;
            } catch (MailException e) {
                // Handle exception if email sending fails (log it or take appropriate action)

                e.printStackTrace();
                return false; // Indicate failure if email sending failed
            }
            // return true; // Password successfully updated
        }
        return false; // Password update failed

    }

    //to save department id in department admin table
    @Override
    public DepartmentDTO findDepartmentByName(String departmentName) {

        System.out.println("findDepartmentByName method running in AdminServiceImpl..");

        DepartmentDTO departmentid1= adminRepo.findDepartmentByName(departmentName);
        System.out.println(departmentid1+"**********");
        if(departmentid1!=null)
        {
            System.out.println("findDepartmentByName successful in AdminServiceImpl");
            return departmentid1;
        }

        else
        {
            System.out.println("findDepartmentByName not successful in AdminServiceImpl..");
        }
        return null;
    }


    //************************************************************************
   @Override
    public List<DepartmentDTO> getAllDepartments() {

        return adminRepo.getAllDepartments();
       // return Collections.emptyList();
    }


    //***********************************************************************
    //department admin can view particular department raise complaint details
    @Override
    public List<RaiseComplaintDTO> getParticularDepartments(String complaintType) {
        System.out.println("getParticularDepartments method running in AdminServiceImpl..");
       List<RaiseComplaintDTO> getParticularDepartment= adminRepo.getParticularDepartments(complaintType);
        log.info("RaiseComplaintDTO : {}", getParticularDepartment);

        if(!getParticularDepartment.isEmpty())
        {
          log.info("RaiseComplaintDTO data successful in AdminServiceImpl");
          return getParticularDepartment;
        }

        else
        {
            log.info("RaiseComplaintDTO data not successful in AdminServiceImpl");
        }

        return Collections.emptyList();
    }

}


