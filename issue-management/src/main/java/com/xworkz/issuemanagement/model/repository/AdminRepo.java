package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;

public interface AdminRepo {



    public AdminDTO findByEmailAndPassword(String email,String password);
}
