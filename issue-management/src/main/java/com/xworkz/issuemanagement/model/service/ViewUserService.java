package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ViewUserService {


    SignUpDTO getUserByEmail(String email);

    String getSignedInUserEmail();
}
