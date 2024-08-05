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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/") // Root mapping
@SessionAttributes({"signUpDTO", "editProfileImageDTO"})
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
    public String editUserProfile( Model model) {
        String signedInUserEmail = (String) httpSession.getAttribute("signedInUserEmail");


        if (signedInUserEmail != null && signedInUserEmail.equals(signedInUserEmail)) {
            SignUpDTO signUpDTO = editUserProfileService.getUserDetails(signedInUserEmail);
            if (signUpDTO != null) {
                model.addAttribute("editSignUpDTO", signUpDTO);
                return "EditUserProfile"; // Assuming EditUserProfile.jsp exists
            }
        }
        model.addAttribute("errorMessage", "Error fetching user details");
        return "EditUserProfile";
    }

    @PostMapping("/edit-profile") // In this image also uploading
    public String updateUserProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        log.info("updateUserProfile method running EditUserProfileController...");

        try {
            String newFileName = null;
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                newFileName = signUpDTO.getEmail() + "_" + originalFilename;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                log.info("path: {}", path);
                Files.write(path, file.getBytes());
                signUpDTO.setImageName(newFileName);

                // Set all previous images inactive
                imageUploadService.setAllImagesInactiveForUser(signUpDTO.getId());

                // Check if image details already exist for the user
                Optional<EditProfileImageDTO> existingImage = imageUploadService.getImageDetailsByUserId(signUpDTO.getId());

                if (existingImage.isPresent()) {
                    //update existing image details
                    EditProfileImageDTO editProfileImageDTO = existingImage.get();
                    editProfileImageDTO.setImagePath(newFileName);
                    editProfileImageDTO.setImageName(originalFilename);
                    editProfileImageDTO.setImageSize(file.getSize());
                    editProfileImageDTO.setImageType(file.getContentType());
                    editProfileImageDTO.setUpdatedBy(signUpDTO.getEmail());
                    editProfileImageDTO.setUpdatedOn(LocalDateTime.now());
                    editProfileImageDTO.setStatus("Active");
                    imageUploadService.updateImageDetails(editProfileImageDTO);  //update
                } else {
                    // Save image details in database for the first time
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
                    editProfileImageDTO.setStatus("Active");
                    imageUploadService.saveImageDetails(editProfileImageDTO); //save data
                }
            }
            //editProfile details updating
            SignUpDTO updatedUserData = editUserProfileService.updateUserDetails(signUpDTO);
            if (updatedUserData != null) {
                redirectAttributes.addFlashAttribute("profileUploadMsg", "Profile updated successfully");
                httpSession.setAttribute("email", updatedUserData.getEmail());
                httpSession.setAttribute("firstName", updatedUserData.getFirstName());
                httpSession.setAttribute("lastName", updatedUserData.getLastName());
                httpSession.setAttribute("contactNumber", updatedUserData.getContactNumber());


                //set imageUrl in session
                if (newFileName != null) {
                    String imageUrl = "/images/" + newFileName;
                    httpSession.setAttribute("profileImage", imageUrl);
                    redirectAttributes.addFlashAttribute("imageURL", imageUrl);
                }
                log.info("Image upload");
                log.info("file getName: {}", file.getName());
                log.info("file getContentType: {}", file.getContentType());
                log.info("file getResource: {}", file.getResource());
                log.info("file getOriginalFilename: {}", file.getOriginalFilename());
                log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());
                return "redirect:/edit?email=" + signUpDTO.getEmail(); // Redirect to avoid form resubmission
            } else {
                redirectAttributes.addFlashAttribute("message", "Profile update failed, User not found.");
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            log.error("Error uploading file", e);
        }
        return "redirect:/edit?email=" + signUpDTO.getEmail(); // Handle error appropriately
    }
}
