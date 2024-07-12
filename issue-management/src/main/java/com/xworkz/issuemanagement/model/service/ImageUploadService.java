package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;

import java.util.Optional;

public interface ImageUploadService {


    boolean saveImageDetails(EditProfileImageDTO editProfileImageDTO);

    Optional<EditProfileImageDTO> getImageDetailsByUserId(int id);


//service
}
//void saveImageDetails(ImageDownloadDTO imageDTO);
//
//Optional<ImageDownloadDTO> getImageDetailsByUserId(int userId);
//
//void deactivateAllImagesForUser(int userId);