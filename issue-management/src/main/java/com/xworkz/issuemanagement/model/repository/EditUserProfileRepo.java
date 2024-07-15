package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface EditUserProfileRepo {


    SignUpDTO findByEmail(String email);

    void updateUserDetails(SignUpDTO signUpDTO);

}
