package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxRepo {




    SignUpDTO existsByEmail(String email);



    SignUpDTO existsByContactNumber(Long contactNumber);


}
