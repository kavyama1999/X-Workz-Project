package com.xworkz.issuemanagement.dto;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@ToString
@Slf4j
@Entity

@Table(name = "admin_table")
public class AdminDTO {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "admin_name")
    private String  name;



    private String email;



    private String  password;

}
