package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.util.EmailPasswordGenerator;

import java.time.LocalDateTime;

public interface SignUpService {

//save data
    public boolean saveAndValidate(SignUpDTO signUpDTO);


    //set
    public void setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive);





//    //to call generate password in service ..so we have to write 1 method
//    SignUpDTO findByEmailAndPassword(String email, String password);
//
//
//    public String generateRandomPassword();


}
