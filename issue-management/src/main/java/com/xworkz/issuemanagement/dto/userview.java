//package com.xworkz.myProject.controller;
//
//import com.xworkz.myProject.Service.EditInfoService;
//import com.xworkz.myProject.Service.FileUploadService;
//import com.xworkz.myProject.Service.SignUpService;
//import com.xworkz.myProject.dto.FileUploadDTO;
//import com.xworkz.myProject.dto.SignUpDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/")
//public class FileUploadController {
//
//    @Autowired
//    private EditInfoService editInfoService;
//
//    @Autowired
//    private FileUploadService fileUploadService;
//
//    @Autowired
//    private SignUpService signUpService;
//
//    private static final String UPLOAD_DIR = "C:\\images";
//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file, SignUpDto signUpDTO, Model model, HttpSession session) {
//        if (file.isEmpty()) {
//            model.addAttribute("message", "Please select a file to upload");
//            return "EditProfile";
//        }
//        try {
//            String originalFilename = file.getOriginalFilename();
//            String newFilename = signUpDTO.getEmail() + "_" + originalFilename;
//            System.out.println("File name" + newFilename);
//            Path path = Paths.get(UPLOAD_DIR, newFilename);
//            System.out.println("path" + path);
//            Files.write(path, file.getBytes());
//            signUpDTO.setProfileImage(newFilename);
//
//            Optional<SignUpDto> optionalUser = signUpService.findUserByEmail(signUpDTO.getEmail());
//            if (!optionalUser.isPresent()) {
//                model.addAttribute("message", "User not found.");
//                return "EditProfile";
//            }
//
//            SignUpDto user = optionalUser.get();
//
//            // Set all previous images to inactive
//            fileUploadService.setAllImagesInactiveForUser(user.getId());
//
//            FileUploadDTO fileUploadDTO = new FileUploadDTO();
//            fileUploadDTO.setName(newFilename);
//            fileUploadDTO.setType(file.getContentType());
//            fileUploadDTO.setSize(file.getSize());
//            fileUploadDTO.setUser(user);
//            fileUploadDTO.setCreatedAt(LocalDateTime.now());
//            fileUploadDTO.setCreatedBy(signUpDTO.getEmail());
//            fileUploadDTO.setUpdatedAt(LocalDateTime.now());
//            fileUploadDTO.setUpdatedBy(signUpDTO.getEmail());
//            fileUploadDTO.setStatus("Active");
//            fileUploadService.saveImageDetails(fileUploadDTO);
//
//            SignUpDto updatedDTO = editInfoService.updateUserProfile(signUpDTO.getEmail(), signUpDTO);
//            if (updatedDTO != null) {
//                model.addAttribute("message", "Profile updated successfully!");
//                session.setAttribute("profileImage", "/images/" + newFilename);
//                session.setAttribute("email", updatedDTO.getEmail());
//                session.setAttribute("firstName", updatedDTO.getFirstName());
//                session.setAttribute("lastName", updatedDTO.getLastName());
//                session.setAttribute("phoneNumber", updatedDTO.getPhoneNo());
//            } else {
//                model.addAttribute("message", "Profile update failed. User not found.");
//            }
//
//            String imageUrl = "/images/" + newFilename;
//            session.setAttribute("profileImage", imageUrl);
//
//            model.addAttribute("imageURL", imageUrl);
//            model.addAttribute("dto", signUpDTO);
//
//        } catch (IOException e) {
//            model.addAttribute("message", "File upload failed: " + e.getMessage());
//        }
//        return "EditProfile";
//    }
//}