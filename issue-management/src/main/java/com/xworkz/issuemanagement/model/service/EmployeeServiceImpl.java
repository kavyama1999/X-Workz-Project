package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired  //to get the reference class
    private EmployeeRepo employeeRepo;

    @Override
    public boolean saveEmployeeData(EmployeeDTO employeeDTO) {

        log.info("saveEmployeeData method running in EmployeeServiceImpl..");

        boolean employeeData = employeeRepo.saveEmployeeData(employeeDTO);

        if(employeeData)
        {
         log.info("employeeData saved successfully in EmployeeServiceImpl.. ");
         return true;
        }

        else

        {
            log.info("employeeData not saved successfully in EmployeeServiceImpl..");
        }
        return false;
    }

    //****************************************************************

    //to check whether emailId exists or not in database
    @Override
    public EmployeeDTO findByEmail(String emailId) {

        log.info("findByEmail method running EmployeeServiceImpl..");

       EmployeeDTO employeeDTO= employeeRepo.findByEmail(emailId);

       if(employeeDTO!=null)
       {
           log.info("EmailId exists in database");
           return employeeDTO;
       }

       else
       {
           log.info("EmailId not exists in database");
       }


        return null;
    }
}
