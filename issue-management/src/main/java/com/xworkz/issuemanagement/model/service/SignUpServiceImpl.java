package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailsending.MailSend;
import com.xworkz.issuemanagement.model.repository.MailRepo;
import com.xworkz.issuemanagement.model.repository.SignUpRepo;
import com.xworkz.issuemanagement.util.EmailPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class SignUpServiceImpl implements SignUpService {


    private static final Logger log = LoggerFactory.getLogger(SignUpServiceImpl.class);
    @Autowired
    private SignUpRepo signUpRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private MailRepo mailRepo;

    @Autowired
    private MailSend mailSend;





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


        //image set in profile

        signUpDTO.setImageName("profileicon.jpg");


        //generating password to stored in database
        String generatedPassword = EmailPasswordGenerator.generatePassword();

        //decode
        signUpDTO.setPassword(passwordEncoder.encode(generatedPassword));



        boolean data = this.signUpRepo.userDataSave(signUpDTO);

        if (data) {
            System.out.println("SignUpRepo successful in SignUpServiceImpl:" + signUpDTO);
            signUpDTO.setPassword(generatedPassword);

            //mail send class
            mailSend.sendPassword(signUpDTO);
            log.info("your decoded password is :"+generatedPassword);


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
