package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.EditUserProfileService;
import com.xworkz.issuemanagement.model.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/") // Root mapping
@SessionAttributes("signUpDTO")
@Slf4j
public class EditUserProfileController {

    private static final String UPLOAD_DIR = "C:\\Users\\kavya\\issue-management-images";

    @Autowired
    private EditUserProfileService editUserProfileService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("edit")
    public String editUserProfile(@RequestParam("email") String email, Model model) {
        String signedInUserEmail = (String) httpSession.getAttribute("signedInUserEmail");
        if (signedInUserEmail != null && signedInUserEmail.equals(email)) {
            SignUpDTO signUpDTO = editUserProfileService.getUserDetails(email);
            if (signUpDTO != null) {
                model.addAttribute("editSignUpDTO", signUpDTO);
                return "EditUserProfile"; // Assuming EditUserProfile.jsp exists
            }
        }
        model.addAttribute("errorMessage", "Error fetching user details");
        return "ErrorPage"; // Handle error appropriately
    }

    @PostMapping("/edit-profile") // In this image also uploading
    public String updateUserProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file") MultipartFile file, HttpSession httpSession) {
        try {
            String newFileName = null;
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                newFileName = signUpDTO.getEmail() + "_" + originalFilename;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                log.info("path: {}", path);
                Files.write(path, file.getBytes());
                signUpDTO.setImageName(newFileName);

                // Save image details in database
                EditProfileImageDTO editProfileImageDTO = new EditProfileImageDTO();
                editProfileImageDTO.setUser(signUpDTO); // Set the user
                editProfileImageDTO.setImagePath(newFileName); // Set the image path
                editProfileImageDTO.setImageName(originalFilename);
                editProfileImageDTO.setImageSize(file.getSize());
                editProfileImageDTO.setImageType(file.getContentType());
                editProfileImageDTO.setCreatedBy(signUpDTO.getEmail());
                editProfileImageDTO.setCreatedOn(LocalDateTime.now());
                editProfileImageDTO.setUpdatedBy(signUpDTO.getEmail());
                editProfileImageDTO.setUpdatedOn(LocalDateTime.now());

                imageUploadService.saveImageDetails(editProfileImageDTO);
            }

            SignUpDTO updatedUserData = editUserProfileService.updateUserDetails(signUpDTO);
            if (updatedUserData != null) {
                model.addAttribute("signUpDTO", updatedUserData);
                model.addAttribute("profileUploadMsg", "Profile updated successfully");
                httpSession.setAttribute("email", updatedUserData.getEmail());
                httpSession.setAttribute("firstName", updatedUserData.getFirstName());
                httpSession.setAttribute("lastName", updatedUserData.getLastName());
                httpSession.setAttribute("contactNumber", updatedUserData.getContactNumber());

                if (newFileName != null) {
                    String imageUrl = "/images/" + newFileName;
                    httpSession.setAttribute("profileImage", imageUrl);
                    model.addAttribute("imageURL", imageUrl);
                }

                // Display in console
                log.info("Image upload");
                log.info("file getName: {}", file.getName());
                log.info("file getContentType: {}", file.getContentType());
                log.info("file getResource: {}", file.getResource());
                log.info("file getOriginalFilename: {}", file.getOriginalFilename());
                log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());

                return "EditUserProfile"; // Redirect to edit profile page
            } else {
                model.addAttribute("message", "Profile update failed, User not found.");
            }
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            log.error("Error uploading file", e);
        }

        return "Profile"; // Handle error or success case
    }
}
