package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private ImageUploadRepo imageUploadRepo;


    @Override
    public boolean saveImageDetails(EditProfileImageDTO editProfileImageDTO) {

        log.info("saveImageDetails method running in ImageUploadServiceImpl..");

        boolean imageData=  imageUploadRepo.saveImage(editProfileImageDTO);

        if(imageData)
        {
            log.info("imageUploadRepo in ImageUploadServiceImpl");
        }

        else
        {
            log.info("imageUploadRepo not in ImageUploadServiceImpl");
        }


        return true;
    }


    @Override
    public Optional<EditProfileImageDTO> getImageDetailsByUserId(int id) {
        return imageUploadRepo.findByUserId(id);
    }

    @Override
    public void updateImageDetails(EditProfileImageDTO editProfileImageDTO) {

        log.info("updateImageDetails method running in ImageUploadServiceImpl..");
        imageUploadRepo.imageUpdateDetails(editProfileImageDTO);
    }

    @Override
    public void setAllImagesInactiveForUser(int id) {
        log.info("setAllImagesInactiveForUser method running in ImageUploadServiceImpl..");
        imageUploadRepo.SetAllImagesInactiveForUser(id);
    }


}
