package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RegisterDepartmentAdminDTO;

public interface AjaxService {


    boolean existsByEmail(String email);

    boolean existsByContactNumber(Long contactNumber);



    //for DepartmentAdmin register ajax

    boolean existsByEmailId(String email);

    boolean checkExistsByContactNumber(Long contactNumber);

}
