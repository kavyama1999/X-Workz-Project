package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.ViewUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewUserServiceImpl implements ViewUserService {



    @Autowired
    private ViewUserRepo viewUserRepo;

    @Override
    public SignUpDTO getUserByEmail(String email) {
        return viewUserRepo.findByEmail(email);
    }

    @Override
    public String getLoggedInUserEmail() {
        return "";
    }
}
