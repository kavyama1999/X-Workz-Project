package com.xworkz.issuemanagement.dto;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity   //mapping to database
@Slf4j
@Data
@ToString
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
    @JoinColumn(name = "department_id",referencedColumnName = "department_id")  //name is employee table we added department_id
    private DepartmentDTO departmentId;


//    //its coming from department table
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
//    private DepartmentDTO departmentDTO;

}
