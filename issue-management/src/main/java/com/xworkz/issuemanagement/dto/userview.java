//////package com.xworkz.xworkzProject.dto;
//////
//////import lombok.*;
//////
//////import javax.persistence.*;
//////import java.time.LocalDateTime;
//////
////////@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
////////@Entity
////////@Table(name = "complaint")
////////public class ComplaintsDTO {
////////
////////
////////    @Id
////////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////////    private int id;
////////
//////////    @Column(name = "type")
//////////    private String type;
////////
////////    @Column(name = "area")
////////    private String area;
////////
////////    @Column(name = "address")
////////    private String address;
////////
////////    @Column(name = "country")
////////    private String country;
////////
////////    @Column(name = "state")
////////    private String state;
////////
////////    @Column(name = "city")
////////    private String city;
////////
////////    @Column(name = "description")
////////    private String description;
////////
////////    @Column(name = "user_id")
////////    private int userId;
////////
////////    @Column(name = "created_by")
////////    private String createdBy;
////////
////////    @Column(name = "created_at")
////////    private LocalDateTime createdAt;
////////
////////    @Column(name = "modified_by")
////////    private String modifiedBy;
////////
////////    @Column(name = "modified_at")
////////    private LocalDateTime modifiedAt;
////////
////////    @Column(name = "status")
////////    private String status;
////////
////////
////////    @Column(name="department_id")
////////    private Integer departmentId;
//////
//////}
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
//////]]]]]]]]]]]]]]]]]]]]
//////@Override
//////public void setAllImagesInactiveForUser(int userId) {
//////    EntityManager entityManager = entityManagerFactory.createEntityManager();
//////    try {
//////        entityManager.getTransaction().begin();
//////        Query query = entityManager.createQuery(
//////                "UPDATE FileUploadDTO SET status = 'Inactive' WHERE user.id = :userId");
//////        query.setParameter("userId", userId);
//////        query.executeUpdate();
//////        entityManager.getTransaction().commit();
//////    } finally {
//////        entityManager.close();
//////    }
//////}
//////}
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////package com.xworkz.issuemanagement.controller;
////
////import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
////import com.xworkz.issuemanagement.dto.SignUpDTO;
////import com.xworkz.issuemanagement.model.service.EditUserProfileService;
////import com.xworkz.issuemanagement.model.service.ImageUploadService;
////import lombok.extern.slf4j.Slf4j;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.multipart.MultipartFile;
////
////import javax.servlet.http.HttpSession;
////import java.io.IOException;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.Paths;
////import java.time.LocalDateTime;
////import java.util.Optional;
////
//////@Controller
//////@RequestMapping("/") // Root mapping
//////@SessionAttributes({"signUpDTO", "editProfileImageDTO"})
//////@Slf4j
////////public class EditUserProfileController {
////////
//////    private static final String UPLOAD_DIR = "C:\\Users\\kavya\\issue-management-images";
//////
//////    @Autowired
//////    private EditUserProfileService editUserProfileService;
//////
//////    @Autowired
//////    private ImageUploadService imageUploadService;
//////
//////    @Autowired
//////    private HttpSession httpSession;
//////
//////    @GetMapping("edit")
//////    public String editUserProfile(@RequestParam("email") String email, Model model) {
//////        String signedInUserEmail = (String) httpSession.getAttribute("signedInUserEmail");
//////        if (signedInUserEmail != null && signedInUserEmail.equals(email)) {
//////            SignUpDTO signUpDTO = editUserProfileService.getUserDetails(email);
//////            if (signUpDTO != null) {
//////                model.addAttribute("editSignUpDTO", signUpDTO);
//////                return "EditUserProfile"; // Assuming EditUserProfile.jsp exists
//////            }
//////        }
//////        model.addAttribute("errorMessage", "Error fetching user details");
//////        return "ErrorPage"; // Handle error appropriately
//////    }
//////
//////    @PostMapping("/edit-profile") // In this image also uploading
//////    public String updateUserProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file") MultipartFile file, HttpSession httpSession) {
//////        log.info("updateUserProfile method running EditUserProfileController...");
//////
//////        try {
//////            String newFileName = null;
//////            if (file != null && !file.isEmpty()) {
//////                String originalFilename = file.getOriginalFilename();
//////                newFileName = signUpDTO.getEmail() + "_" + originalFilename;
//////                Path path = Paths.get(UPLOAD_DIR, newFileName);
//////                log.info("path: {}", path);
//////                Files.write(path, file.getBytes());
//////                signUpDTO.setImageName(newFileName);
//////
//////                // Set all previous images inactive
//////                imageUploadService.setAllImagesInactiveForUser(signUpDTO.getId());
//////
//////                // Check if image details already exist for the user
//////                Optional<EditProfileImageDTO> existingImage = imageUploadService.getImageDetailsByUserId(signUpDTO.getId());
//////
//////                if (existingImage.isPresent()) {
//////                    // Update existing image details
//////                    EditProfileImageDTO editProfileImageDTO = existingImage.get();
//////                    editProfileImageDTO.setImagePath(newFileName);
//////                    editProfileImageDTO.setImageName(originalFilename);
//////                    editProfileImageDTO.setImageSize(file.getSize());
//////                    editProfileImageDTO.setImageType(file.getContentType());
//////                    editProfileImageDTO.setUpdatedBy(signUpDTO.getEmail());
//////                    editProfileImageDTO.setUpdatedOn(LocalDateTime.now());
//////                    editProfileImageDTO.setStatus("Active");
//////
//////                    imageUploadService.updateImageDetails(editProfileImageDTO); // Update
//////                } else {
//////                    // Save new image details in database
//////                    EditProfileImageDTO editProfileImageDTO = new EditProfileImageDTO();
//////                    editProfileImageDTO.setUser(signUpDTO); // Set the user
//////                    editProfileImageDTO.setImagePath(newFileName); // Set the image path
//////                    editProfileImageDTO.setImageName(originalFilename);
//////                    editProfileImageDTO.setImageSize(file.getSize());
//////                    editProfileImageDTO.setImageType(file.getContentType());
//////                    editProfileImageDTO.setCreatedBy(signUpDTO.getEmail());
//////                    editProfileImageDTO.setCreatedOn(LocalDateTime.now());
//////                    editProfileImageDTO.setUpdatedBy(signUpDTO.getEmail());
//////                    editProfileImageDTO.setUpdatedOn(LocalDateTime.now());
//////                    editProfileImageDTO.setStatus("Active");
//////
//////                    // Image upload service (editProfileImageDTO)
//////                    imageUploadService.saveImageDetails(editProfileImageDTO); // Save data
//////                }
//////            }
//////
//////            // Update profile details
//////            SignUpDTO updatedUserData = editUserProfileService.updateUserDetails(signUpDTO);
//////            if (updatedUserData != null) {
//////                model.addAttribute("signUpDTO", updatedUserData);
//////                model.addAttribute("profileUploadMsg", "Profile updated successfully");
//////                httpSession.setAttribute("email", updatedUserData.getEmail());
//////                httpSession.setAttribute("firstName", updatedUserData.getFirstName());
//////                httpSession.setAttribute("lastName", updatedUserData.getLastName());
//////                httpSession.setAttribute("contactNumber", updatedUserData.getContactNumber());
//////
//////                if (newFileName != null) {
//////                    String imageUrl = "/images/" + newFileName;
//////                    httpSession.setAttribute("profileImage", imageUrl);
//////                    model.addAttribute("imageURL", imageUrl);
//////                }
//////
//////                // Display in console
//////                log.info("Image upload");
//////                log.info("file getName: {}", file.getName());
//////                log.info("file getContentType: {}", file.getContentType());
//////                log.info("file getResource: {}", file.getResource());
//////                log.info("file getOriginalFilename: {}", file.getOriginalFilename());
//////                log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());
//////
//////                return "EditUserProfile"; // Redirect to edit profile page
//////            } else {
//////                model.addAttribute("message", "Profile update failed, User not found.");
//////            }
//////        } catch (IOException e) {
//////            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
//////            log.error("Error uploading file", e);
//////        }
//////
//////        return "Profile"; // Handle error or success case
//////    }
//////}
////
////
////---------------------<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
////<%@ page isELIgnoLUTF-8">
////    <meta name="viewport" content="width=device-width, initial-scale=1.0">
////    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
////    <title>ComplaintRise</title>
////    <style>
////        .error {
////    color: red;
////    font-size: 0.9em;
////}
////    </style>
////   <script src="/crisisManagement/jscript/CrisisSignUp.js"></script>
////</head>
////<body>
////    <nav class="navbar navbar-light "style="background-color:black">
////        <div class="container" style="display: flex; flex-direction: row;">
////            <a class="navbar-brand" href="#">
////               <img src="/crisisManagement/logo/xworklogo.png" alt="xworkz" width="140" height="70">
////            </a>
////            <a class="navbar-brand" href="Home.jsp" style="color: aliceblue;"><b>Dashboard</b></a>
////        </div>
////    </nav>
////    <div class="container mt-3" style="max-width: 600px;">
////        <div class="card p-3">
////            <div class="card-header">
////               <center> <h4><b>ComplaintRise</b></h4></center>
////            </div>
////            <div class="card-body">
////                <form action="complaintRise" method="post" id="CrisisSignUp">
////                    <span style="color:red">
////                        <c:forEach items="${valid}" var="obj">
////${obj.defaultMessage}
////                        </c:forEach>
////                    </span>
////                    <h1 style="color:red">${msg}</h1>
////                    <h1 style="color:red">${failed}</h1>
////                    <h1 style="color:green">${success}</h1>
////                    <div class="form-group mb-2">
////<label >Complaint Type:</label>
////                        <select class="form-control" id="complaintType" name="complaintType" >
////                            <option value="">Select Complaint Type</option>
////                            <option value="electricity">Electricity</option>
////                            <option value="water">Water</option>
////                            <option value="road">Road</option>
////                            <option value="drainage">Drainage</option>
////                            <option value="health">Health</option>
////                                <option value="education">Education</option>
////                                <option value="environment">Environment</option>
////                                <option value="traffic">Traffic</option>
////                                <option value="parking">Parking</option>
////                                <option value="noise">Noise Pollution</option>
////                                <option value="security">Security Concerns</option>
////                                <option value="housing">Housing Issues</option>
////                        </select>
////                    </div>
////                    <div class="form-group mb-2">
////                        <label for="country">Country:</label>
////                        <select class="form-control country" id="country" name="country" onchange="loadStates()"  >
////                            <option value="">Select Country</option>
////                        </select>
////                    </div>
////                    <div class="form-group mb-2">
////                        <label for="state">State:</label>
////                        <select class="form-control state" id="state" name="state" onchange="loadCities()" disabled>
////                            <option value="">Select State</option>
////                        </select>
////                    </div>
////                    <div class="form-group mb-2">
////                        <label for="city">City:</label>
////                        <select class="form-control city" id="city"   name="city" >
////                            <option value="">Select City</option>
////                        </select>
////                    </div>
////                    <div class="form-group mb-2">
////                        <label for="message">Description:</label>
////                        <textarea class="form-control" id="message" name="message" rows="3" maxlength="1000" oninput="updateCounter()"></textarea>
////                        <div class="counter">
////                            <span id="charCount">0</span> / 1000 characters used
////                        </div>
////                    </div>
////                    <div class="mb-2">
////                        <label for="area" class="form-label">Address:</label>
////                        <input type="text" class="form-control" id="address" name="address" >
////                    </div>
////        &nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp
////
////        <input type="submit" class="btn btn-dark" value="RiseComplaint" name="RiseComplaint">
////                    <input type="reset" class="btn btn-dark "style="width: 130px; value="Reset" name="Reset">
////                </form>
////
////<script>
////function updateCounter() {
////    var message = document.getElementById("message");
////    var charCount = document.getElementById("charCount");
////    var maxLength = message.getAttribute("maxlength");
////    var currentLength = message.value.length;
////
////    charCount.textContent = currentLength;
////}
////
////var config = {
////        countriesUrl: 'https://api.countrystatecity.in/v1/countries',
////statesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states',
////citiesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states/[siso]/cities',
////ckey: 'ODIzTTVJUGVIVWdQT1ZJbk1McU50RnJ0ZmtTUVNBcERseFdTb25SSA=='
////        };
////
////        var countrySelect = document.querySelector('.country'),
////                stateSelect = document.querySelector('.state'),
////                citySelect = document.querySelector('.city');
////
////function loadCountries() {
////    fetch(config.countriesUrl, { headers: { "X-CSCAPI-KEY": config.ckey } })
////                            .then(response => response.json())
////                            .then(data => {
////            data.forEach(country => {
////                                    const option = document.createElement('option');
////    option.value = country.iso2;
////    option.textContent = country.name;
////    countrySelect.appendChild(option);
////                                });
////                            })
////                            .catch(error => console.error('Error loading countries:', error));
////
////    stateSelect.disabled = true;
////    citySelect.disabled = true;
////    stateSelect.style.pointerEvents = 'none';
////    citySelect.style.pointerEvents = 'none';
////}
////
////function loadStates() {
////    stateSelect.disabled = false;
////    citySelect.disabled = true;
////    stateSelect.style.pointerEvents = 'auto';
////    citySelect.style.pointerEvents = 'none';
////
////                        const selectedCountryCode = countrySelect.value;
////    stateSelect.innerHTML = '<option value="">Select State</option>';
////    citySelect.innerHTML = '<option value="">Select City</option>';
////
////    fetch(config.statesUrl.replace('[ciso]', selectedCountryCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
////                            .then(response => response.json())
////                            .then(data => {
////            data.forEach(state => {
////                                    const option = document.createElement('option');
////    option.value = state.iso2;
////    option.textContent = state.name;
////    stateSelect.appendChild(option);
////                                });
////                            })
////                            .catch(error => console.error('Error loading states:', error));
////}
////
////function loadCities() {
////    citySelect.disabled = false;
////    citySelect.style.pointerEvents = 'auto';
////
////                        const selectedCountryCode = countrySelect.value;
////                        const selectedStateCode = stateSelect.value;
////
//////    citySelect.innerHTML = '<option value="">Select City</option>';
//////
//////    fetch(config.citiesUrl.replace('[ciso]', selectedCountryCode).replace('[siso]', selectedStateCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
//////                            .then(response => response.json())
//////                            .then(data => {
//////            data.forEach(city => {
//////                                    const option = document.createElement('option');
//////    option.value = city.name;
//////    option.textContent = city.name;
//////    citySelect.appendChild(option);
//////                                });
//////                            })
//////                            .catch(error => console.error('Error loading cities:', error));
//////}
//////
//////window.onload = loadCountries;
//////                </script>
//////            </div>
//////        </div>
//////    </div>
//////</body>
//////</html>
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//******************************************
//        package com.xworkz.issuemanagement.controller;
//
//import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
//import com.xworkz.issuemanagement.dto.SignUpDTO;
//import com.xworkz.issuemanagement.model.service.RaiseComplaintService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@Slf4j
//@RequestMapping("/")
//public class RaiseComplaintController {
//
//    @Autowired
//    private RaiseComplaintService raiseComplaintService;
//
//    public RaiseComplaintController() {
//        log.info("No argument constructor for RaiseComplaintController...");
//    }
//
//    @PostMapping("raise-complaint")
//    public String raiseComplaint(RaiseComplaintDTO raiseComplaintDTO, RedirectAttributes redirectAttributes) {
//        log.info("raiseComplaint method running in RaiseComplaintController..");
//
//        System.out.println("RaiseComplaintController: " + raiseComplaintDTO);
//
//
//        // Get the signed-in user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SignUpDTO signedInUser = (SignUpDTO) authentication.getPrincipal();
//
//        // Set the signed-in user in the complaint DTO
//        raiseComplaintDTO.setSignUpDTO(signedInUser);
//
//
//        boolean dataValid = raiseComplaintService.saveRaiseComplaintData(raiseComplaintDTO);
//
//        if (dataValid) {
//            log.info("RaiseComplaintService registration successful in RaiseComplaintController.");
//            redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration Successful: " + raiseComplaintDTO.getComplaintId());
//        } else {
//            log.info("RaiseComplaintService registration not successful in RaiseComplaintController..");
//            redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration failed");
//        }
//
//        return "redirect:/raise-complaint";
//    }
//
//    @GetMapping("raise-complaint")
//    public String showRaiseComplaintPage(Model model) {
//        return "RaiseComplaint";
//    }
//}
//
////    @GetMapping("find-complaint")
////    public String findComplaintByUserId(@RequestParam("userId") int userId, Model model) {
////        log.info("findComplaintByUserId method running in RaiseComplaintController..");
////
////        RaiseComplaintDTO complaint = raiseComplaintService.findByUserId(userId);
////
////        if (complaint != null) {
////            log.info("Complaint found for user ID: {}", userId);
////            model.addAttribute("complaint", complaint);
////        } else {
////            log.info("No complaint found for user ID: {}", userId);
////            model.addAttribute("error", "No complaint found for user ID: " + userId);
////        }
////
////        return "ViewComplaint";
////    }
//
