package com.xworkz.issuemanagement.dto;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RaiseComplaintsDTO {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

private String type;



private String area;

private String  country;


private String state;
}
