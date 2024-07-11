package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.EditUserProfileRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
@Slf4j
public class EditUserProfileServiceImpl implements EditUserProfileService {


    @Autowired
    private EditUserProfileRepo editUserProfileRepo;


    @Autowired
    private HttpSession httpSession;

    @Override
    public SignUpDTO getUserDetails(String email) {

        SignUpDTO signUpDTO = editUserProfileRepo.findByEmail(email);

        return signUpDTO;

        //  return editUserDetailsRepo.findByEmail(email);

    }

    @Override
    public SignUpDTO updateUserDetails(SignUpDTO signUpDTO) {

        log.info("updateUserDetails method running in EditUserProfileServiceImpl..");

        //Set audit fields

        String updatedBy = signUpDTO.getFirstName();
        LocalDateTime updatedOn = LocalDateTime.now();


        setAudit(signUpDTO,updatedBy,updatedOn);


        // httpSession.getAttribute("signedInUserEmail");
        editUserProfileRepo.updateUserDetails(signUpDTO);


        return signUpDTO;
    }

    @Override
    public String getSignedInUserEmail() {
        httpSession.getAttribute("signedInUserEmail");

        return "String";
    }

    @Override
    public void setAudit(SignUpDTO signUpDTO, String updatedBy, LocalDateTime updatedOn) {

      log.info("setAudit method running in EditUserProfileServiceImpl.. ");
      signUpDTO.setUpdatedBy(updatedBy);
      signUpDTO.setUpdatedOn(updatedOn);

    }
}
