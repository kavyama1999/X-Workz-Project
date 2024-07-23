package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service


public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public boolean findByEmailAndPassword(String email, String password) {

        System.out.println("findByEmailAndPassword method in Service Implementation");

        AdminDTO data = adminRepo.findByEmailAndPassword(email, password);

        if (data != null) {
            System.out.println("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDTO> findById(SignUpDTO signUpDTO) {

        System.out.println("findById method in AdminServiceImpl..");
      List<SignUpDTO> dtoData=  adminRepo.findById(signUpDTO);
      if(dtoData!=null)
      {
          System.out.println("findById data successful in AdminServiceImpl..");
          return dtoData;
      }
      else
      {
          System.out.println("findById data not successful in AdminServiceImpl..");
      }
        return Collections.emptyList();
    }
}
