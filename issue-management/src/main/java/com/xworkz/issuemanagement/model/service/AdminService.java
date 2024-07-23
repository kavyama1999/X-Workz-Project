package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.AdminDTO;

public interface AdminService {

    public boolean findByEmailAndPassword(String email,String password);
}
