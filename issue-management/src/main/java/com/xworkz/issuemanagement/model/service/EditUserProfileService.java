package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.time.LocalDateTime;

public interface EditUserProfileService {

    SignUpDTO getUserDetails(String email);

    SignUpDTO updateUserDetails(SignUpDTO signUpDTO);

    String getSignedInUserEmail();

    public void setAudit(SignUpDTO signUpDTO,  String updatedBy, LocalDateTime updatedOn);

//imageUpload



  //  public void setAudit(EditProfileImageDTO editProfileImageDTO,String  updatedBy,LocalDateTime updatedOn);

}
