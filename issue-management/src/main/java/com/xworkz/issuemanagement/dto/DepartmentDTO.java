package com.xworkz.issuemanagement.dto;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@ToString
@Slf4j
@Entity


@Table(name = "department_table")
public class DepartmentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int id;



    @Column(name = "department_address")
    private  String  address;


    @Column(name = "department_name")
    private String departmentName;


    @Column(name = "department_area")
    private String departmentArea;


    @Column(name = "no_of_employees")
    private int noOfEmployees;


}
