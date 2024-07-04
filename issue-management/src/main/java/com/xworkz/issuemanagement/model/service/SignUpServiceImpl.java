package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.SignUpRepo;
import com.xworkz.issuemanagement.util.EmailPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class SignUpServiceImpl implements SignUpService {


    @Autowired
    private SignUpRepo signUpRepo;

    public SignUpServiceImpl() {
        System.out.println("No param Constructor created for SignUpServiceImpl.. ");
    }


    @Override
    public boolean saveAndValidate(SignUpDTO signUpDTO) {

        System.out.println("saveAndValidate method running in SignUpServiceImpl");


        // Set audit fields
        String createdBy = signUpDTO.getFirstName(); // or get the current user
        LocalDateTime createdOn = LocalDateTime.now();

        String updatedBy = signUpDTO.getFirstName(); // or get the current user
        LocalDateTime updatedOn = LocalDateTime.now();

        boolean isActive = true;

        setAudit(signUpDTO, createdBy, createdOn, updatedBy, updatedOn, isActive);


        //generating password to stored in database
        String generatedPassword = EmailPasswordGenerator.generatePassword();
        signUpDTO.setPassword(generatedPassword);


        boolean data = this.signUpRepo.userDataSave(signUpDTO);

        if (data) {
            System.out.println("SignUpRepo successful in SignUpServiceImpl:" + signUpDTO);
            return data;
        } else {
            System.out.println("SignUpRepo not successful in SignUpServiceImpl:" + signUpDTO);
        }
        return true;
    }

    @Override
    public void setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive) {


        System.out.println("setAudit method running in SignInServiceImpl..");
        signUpDTO.setCreatedBy(createdBy);
        signUpDTO.setCreatedOn(createdOn);
        signUpDTO.setUpdatedBy(updatedBy);
        signUpDTO.setUpdatedOn(updatedOn);
        signUpDTO.setActive(isActive);
    }
}
