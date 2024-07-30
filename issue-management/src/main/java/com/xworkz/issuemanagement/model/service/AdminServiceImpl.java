package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.*;
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
        List<SignUpDTO> dtoData = adminRepo.findById(signUpDTO);
        if (dtoData != null) {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO) {
        System.out.println("findById method in AdminServiceImpl(Raise complaintDTO)");

        List<RaiseComplaintDTO> data = adminRepo.findById(raiseComplaintDTO);
        if (data != null) {
            System.out.println("findById  data successful in AdminServiceImpl");
            return data;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl");
        }
        return Collections.emptyList();
    }


    //type AND city

    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeORCity method running in AdminServiceImpl..");
        List<RaiseComplaintDTO> data = adminRepo.searchByComplaintTypeAndCity(complaintType, city);

        if (!data.isEmpty()) {
            System.out.println("searchByComplaintTypeORCity successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("searchByComplaintTypeORCity not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }


    //search by complaint type OR city
    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl..");

        List<RaiseComplaintDTO> listOfData = adminRepo.searchByComplaintTypeOrCity(complaintType, city);
        if (!listOfData.isEmpty()) {
            System.out.println("searchComplaintTypeAndCity successful in AdminServiceImpl");
            return listOfData;
        } else {
            System.out.println("searchByComplaintTypeAndCity not successful in AdminServiceImpl..");
        }

        return Collections.emptyList();
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        System.out.println("saveDepartment method running in AdminServiceImpl..");

        DepartmentDTO data = adminRepo.saveDepartment(departmentDTO);

        System.out.println("data:" + data);

        if (data != null) {
            System.out.println("saveDepartment  successful in AdminServiceImpl..");

            return data;
        } else {
            System.out.println("saveDepartment not successful in AdminServiceImpl..");
        }

        return null;
    }


    //find all department

    @Override
    public List<DepartmentDTO> findAll(String departmentType) {
        System.out.println("findAll method running in AdminServiceImpl..");
        List<DepartmentDTO> data = adminRepo.findAll(departmentType);
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl.. ");

        if (data != null) {
            System.out.println("findAll successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("findAll  not successful in AdminServiceImpl..");
        }
        return null;
    }


    //update department id and Status

    @Override
    public void updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {

        //update status and department id

        System.out.println("updateStatusAndDepartmentId method running in RaiseComplaintService");
        adminRepo.updateStatusAndDepartmentId(complaintId, departmentId, status);
    }

    @Override
    public boolean saveDepartmentAdminData(RegisterDepartmentAdminDTO registerDepartmentAdminDTO) {
        System.out.println("saveDepartmentAdminData method running in saveDepartmentAdminData..");

     boolean saveData= adminRepo.saveDepartmentAdminData(registerDepartmentAdminDTO);

     if(saveData)
     {
         System.out.println("saveDepartmentAdminData saved successfully..");
         return  true;
     }

     else
     {
         System.out.println("saveDepartmentAdminData not saved successfully....");
     }

     return false;
    }

}



