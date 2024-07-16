package com.xworkz.issuemanagement.dto;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j

@Entity
@Table(name = "complaint_raise")
public class RaiseComplaintDTO {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private int complaintId;

    @Column(name = "complaint_type")
    private String complaintType;


    //@Column(name = "country")
    private String country;




    //@Column(name = "state")
    private String state;


    ///@Column(name = "city")
    private String city;


   // @Column(name = "area")
    private String area;


   // @Column(name = "address")
    private String address;


   // @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmplt_id", referencedColumnName = "id")
    //@JoinColumn(name = "user_id", nullable = false)

    //private SignUpDTO user;
    private SignUpDTO signUpDTO;


}
