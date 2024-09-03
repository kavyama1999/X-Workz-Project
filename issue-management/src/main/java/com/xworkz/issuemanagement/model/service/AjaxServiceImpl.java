package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegisterDepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.AjaxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjaxServiceImpl implements AjaxService{

    @Autowired
    private AjaxRepo ajaxRepo;


    @Override
    public boolean existsByEmail(String email) {
        System.out.println("Email:"+email);
        SignUpDTO dto = ajaxRepo.existsByEmail(email);
        if (dto != null)

        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean existsByContactNumber(Long contactNumber)
    {
        System.out.println("contactNumber:" + contactNumber);
        SignUpDTO dto = ajaxRepo.existsByContactNumber(contactNumber);
        return dto != null;
    }




    //**********************************************
    //for subAdmin register page for ajax
    @Override
    public boolean existsByEmailId(String email) {
        System.out.println("Email:"+email);
        RegisterDepartmentAdminDTO dto = ajaxRepo.existsByEmailId(email);
        if (dto != null)

        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean checkExistsByContactNumber(Long contactNumber) {
        System.out.println("contactNumber:" + contactNumber);
        SignUpDTO dto = ajaxRepo.existsByContactNumber(contactNumber);
        return dto != null;

    }


}
