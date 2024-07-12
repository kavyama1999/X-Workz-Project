//package com.xworkz.issuemanagement.controller;
//
//import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
//import com.xworkz.issuemanagement.dto.SignUpDTO;
//import com.xworkz.issuemanagement.model.service.EditUserProfileService;
//import com.xworkz.issuemanagement.model.service.ImageUploadService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//
//@RequestMapping("/issue-management")
//@Slf4j
//@SessionAttributes("signUpDTO")
//public class ImageUploadController {
//
//    @Autowired
//    private ImageUploadService imageUploadService;
//
//    @Autowired
//    private EditUserProfileService editUserProfileService;
//
//    public ImageUploadController() {
//        log.info("No parameters constructor in ImageUploadController..");
//    }
//
//    @PostMapping("/edit-profile")
//    public String uploadImage(@RequestParam("file") MultipartFile file,
//                              @Valid @ModelAttribute("signUpDTO") SignUpDTO signUpDTO,
//                              BindingResult bindingResult, Model model,
//                              HttpSession session) throws IOException {
//
//        log.info("Running uploadImage method in ImageUploadController..");
//
//        if (bindingResult.hasErrors()) {
//            log.error("Validation errors occurred: {}", bindingResult.getAllErrors());
//            model.addAttribute("errorMessageProfile", "Validation errors occurred");
//            return "EditUserProfile";
//        }
//
//        if (file.isEmpty()) {
//            log.error("File is empty");
//            model.addAttribute("errorMessageProfile", "Please select a file to upload");
//            return "EditUserProfile";
//        }
//
//        log.info("Image upload");
//        log.info("file getName: {}", file.getName());
//        log.info("file getSize: {}", file.getSize());
//        log.info("file getContentType: {}", file.getContentType());
//        log.info("file getResource: {}", file.getResource());
//        log.info("file getBytes: {}", file.getBytes());
//        log.info("file getOriginalFilename: {}", file.getOriginalFilename());
//
//        // Save the image file to disk
//        Path path = null;
//        try {
//            byte[] bytes = file.getBytes();
//            path = Paths.get("C:\\Users\\kavya\\issue-management-images", file.getOriginalFilename());
//            Files.write(path, bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log.info("File: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());
//
//        // Set the user for the image
//        EditProfileImageDTO editProfileImageDTO1 = new EditProfileImageDTO();
//        editProfileImageDTO1.setUser(signUpDTO);
//        editProfileImageDTO1.setImagePath(path.toString());
//
//        // Save the image details
//        boolean isSaved = imageUploadService.saveImageDetails(editProfileImageDTO1);
//        if (isSaved) {
//            log.info("saveImageDetails saved in ImageUploadController");
//
//            // Update user details
//            SignUpDTO userData = editUserProfileService.updateUserDetails(signUpDTO);
//
//            if (userData != null) {
//                log.info("Updated Data: {}", userData);
//                model.addAttribute("signUpDTO", userData);
//                model.addAttribute("msg", "Profile updated Successfully!..");
//                return "EditUserProfile";
//            }
//            model.addAttribute("errorMessageProfile", "Error updating profile");
//        } else {
//            log.info("saveImageDetails not saved in ImageUploadController.. ");
//            model.addAttribute("errorMessageProfile", "Error saving image details");
//        }
//
//        return "EditUserProfile";
//    }
//
//}
//
//
//
//
//
