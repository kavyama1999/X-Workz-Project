package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;

import java.util.Optional;

public interface ImageUploadRepo {


    boolean saveImage(EditProfileImageDTO editProfileImageDTO);


    //void saveProfileImage(ProfileImageUploadDto profileImageUploadDto);

    Optional<EditProfileImageDTO> findByUserId(int id);  //id from signUp id


}
