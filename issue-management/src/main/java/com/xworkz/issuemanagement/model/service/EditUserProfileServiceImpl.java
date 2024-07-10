package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.EditUserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class EditUserProfileServiceImpl implements EditUserProfileService {


    @Autowired
    private EditUserProfileRepo editUserProfileRepo;


    @Autowired
    private HttpSession httpSession;

    @Override
    public SignUpDTO getUserDetails(String email) {

      SignUpDTO signUpDTO=  editUserProfileRepo.findByEmail(email);

        return signUpDTO;

        //  return editUserDetailsRepo.findByEmail(email);

    }

    @Override
    public SignUpDTO updateUserDetails(SignUpDTO signUpDTO) {


       // httpSession.getAttribute("signedInUserEmail");
        editUserProfileRepo.updateUserDetails(signUpDTO);


        return signUpDTO;
    }

    @Override
    public String getSignedInUserEmail() {
        httpSession.getAttribute("signedInUserEmail");

        return "String";
    }
}
