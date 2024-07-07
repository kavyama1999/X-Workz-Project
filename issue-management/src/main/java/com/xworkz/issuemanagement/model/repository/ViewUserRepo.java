package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ViewUserRepo {


    SignUpDTO findByEmail(String email);

}
