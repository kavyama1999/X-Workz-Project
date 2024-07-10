package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface EditUserProfileService {

    SignUpDTO getUserDetails(String email);

    SignUpDTO updateUserDetails(SignUpDTO signUpDTO);

    String getSignedInUserEmail();




}
