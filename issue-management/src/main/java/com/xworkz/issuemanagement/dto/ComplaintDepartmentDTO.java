package com.xworkz.issuemanagement.dto;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@ToString
@Slf4j
@Entity


@Table(name="department_table")
public class ComplaintDepartmentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int id;



    @Column(name = "department_type")
    private String  departmentType;


    private String  address;


    @Column(name = "no_of_employees")
    private int noOfEmployees;






}
