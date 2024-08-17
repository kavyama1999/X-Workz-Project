package com.xworkz.issuemanagement.dto;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@ToString
@Entity
@Table(name = "department_admin_table")
public class RegisterDepartmentAdminDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_admin_id")
    private int id;


    @NotEmpty(message = "AdminName cannot be empty")
    @Size(min = 3,max = 30,message = "AdminName should contain letters between > 2 and  <30")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "AdminName should contain only alphabetic letters")
    @Column(name = "admin_name")
    private String adminName;


    @NotEmpty(message = "Please select departmentName")
    @Column(name = "department_name")
    private String departmentName;


    @NotEmpty(message = "Please enter valid email")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Enter valid email")
    @Column(name = "email")
    private String email;


    @NotNull(message = "Please Enter contact number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "contact_number")
    private Long contactNumber;

    @NotNull(message = "Please Enter Alternative contact number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "alternative_contact_number")
    private Long alternateContactNumber;


    @Column(name = "password")
    private String  password;


    //email locked update in database
    @Column(name = "failed_attempt")
    private int failedAttempt=0;


    public static final int MAX_LOGIN_ATTEMPTS=3;
    @Column(name = "account_locked")
    private boolean accountLocked=false;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_department_id",referencedColumnName = "department_id") //parent...left one child
    private DepartmentDTO departmentId;



//    @Column(name = "department_names")
//   // @ManyToOne(fetch =FetchType.LAZY)
//    //@JoinColumn(name = "depart_name",referencedColumnName = "department_name")
//    private String departmentNames;







}
