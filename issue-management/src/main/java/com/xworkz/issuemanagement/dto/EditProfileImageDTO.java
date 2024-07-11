package com.xworkz.issuemanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@ToString
@Slf4j
@Entity

@Table(name = "image_upload")
public class EditProfileImageDTO {

    @Column(name = "image_id")
    private  int imageId;


    @Column(name = "image_name")
    private String imageName;


    @Column(name = "image_size")
    private  long imageSize;

    @Column(name = "")
    private String imageType;


    @Column(name="id")
    private int id;

    @Column(name = "created_by")
    private String createdBy;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "modified_by")
    private String modifiedBy;


    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    //private String status;




//@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString

    public EditProfileImageDTO()
    {
        log.info("No parameter constructor created in EditProfileImageDTO ");
    }




}







