package com.xworkz.issuemanagement.model.service;

import com.sun.org.apache.xpath.internal.axes.WalkingIteratorSorted;
import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ImageUploadService {


    boolean saveImageDetails(EditProfileImageDTO editProfileImageDTO);

    Optional<EditProfileImageDTO> getImageDetailsByUserId(int id);


    /// New method for updating image details
    void updateImageDetails(EditProfileImageDTO editProfileImageDTO); // New method


  //  void setAudit(EditProfileImageDTO editProfileImageDTO , String updatedBy, LocalDateTime updatedOn);
  void setAllImagesInactiveForUser(int id);  // New method declaration


}





//Optional<ImageDownloadDTO> getImageDetailsByUserId(int userId);//its is optonal

//instead of that we can use like this also
//EditProfileImageDTO findByUserId(String email);
