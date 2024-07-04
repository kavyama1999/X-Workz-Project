package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ResetPasswordRepo {


    SignUpDTO findByEmail(String email);


    void updatePassword(String email, String newPassword);
}
