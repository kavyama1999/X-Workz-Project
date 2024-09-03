package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.RegisterDepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxRepo {




    SignUpDTO existsByEmail(String email);



    SignUpDTO existsByContactNumber(Long contactNumber);


//for DepartmentAdmin register ajax

    RegisterDepartmentAdminDTO existsByEmailId(String email);

    RegisterDepartmentAdminDTO checkExistsByContactNumber(Long contactNumber);


}
