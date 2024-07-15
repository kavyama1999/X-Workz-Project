package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;

import java.util.Optional;

public interface ImageUploadRepo {


    boolean saveImage(EditProfileImageDTO editProfileImageDTO);



    Optional<EditProfileImageDTO> findByUserId(int id);  //id from signUp id

  //to update image table

  void imageUpdateDetails(EditProfileImageDTO editProfileImageDTO);

     void SetAllImagesInactiveForUser(int id);

//    EditProfileImageDTO findByUserId(String email);


}
