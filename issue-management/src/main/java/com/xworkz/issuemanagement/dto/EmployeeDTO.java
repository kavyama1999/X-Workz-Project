package com.xworkz.issuemanagement.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

   //mapping to database
@Slf4j
@Data
@ToString
@Getter
@Setter

@Entity
@Table(name = "employee_table")
public class EmployeeDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;



    @Column(name = "employee_name")
    private String  employeeName;


    @Column(name = "department_name")
    private String departmentName;


    @Column(name = "email_id")
    private String  emailId;



    private String address;

    @Column(name = "contact_number")
    private Long contactNumber;


    @Column(name = "alternative_contact_number")
    private Long alternativeContactNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",referencedColumnName = "department_id")  //name ....in employee table we added department_id
    private DepartmentDTO departmentId;



    private Long otp;


    @Column(name = "status")
    private String status;


//    //its coming from department table
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
//    private DepartmentDTO departmentDTO;

}
