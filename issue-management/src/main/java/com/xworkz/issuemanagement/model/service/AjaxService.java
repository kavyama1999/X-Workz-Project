package com.xworkz.issuemanagement.model.service;

public interface AjaxService {


    boolean existsByEmail(String email);

    boolean existsByContactNumber(Long contactNumber);


}
