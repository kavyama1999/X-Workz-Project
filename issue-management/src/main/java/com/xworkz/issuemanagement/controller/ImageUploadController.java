package com.xworkz.issuemanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
@Slf4j
public class ImageUploadController {


    public ImageUploadController()
    {
        log.info("No parameters constructor in ImageUploadController..");
    }


//compile time exception
    @PostMapping("upload-file")
    public String uploadImage(@RequestParam MultipartFile file) throws IOException {

        log.info("Image upload");
        log.info("file getName: {}", file.getName());
        log.info("file getSIze:{}",file.getSize());
        log.info("file getContentType:{}",file.getContentType());
        log.info("file getResource: {}",file.getResource());
        log.info("file getBytes:{}",file.getBytes());
        log.info("file getOriginFileName:{}",file.getOriginalFilename());



        byte[] bytes=file.getBytes();

        Path path= Paths.get("C:\\Users\\kavya\\issue-management-images",file.getOriginalFilename());
        Files.write(path,bytes);

        log.info("file: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());

        return "Profile";
    }




}



