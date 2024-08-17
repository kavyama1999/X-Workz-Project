package com.xworkz.issuemanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Slf4j
@Entity

@Table(name = "image_upload")
public class EditProfileImageDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private  int imageId;


    @Column(name = "image_name")
    private String imageName;


    @Column(name = "image_size")
    private  long imageSize;

    @Column(name = "image_type")
    private String imageType;


//    @Column(name="image_user_id")
//    private int imageUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_user_id", referencedColumnName = "id")//parent ..left one child
    private SignUpDTO user;

    @Column(name = "created_by")
    private String createdBy;


    @Column(name = "created_on")
    private LocalDateTime createdOn;


    @Column(name = "updated_by")
    private String updatedBy;


    @Column(name = "updated_on")
    private LocalDateTime updatedOn;



    @Column(name = "image_path")
    private String  imagePath;

    @Column(name = "status")
    private String status;
    //private String status;




//@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString

    public EditProfileImageDTO()
    {
        log.info("No parameter constructor created in EditProfileImageDTO ");
    }




}







