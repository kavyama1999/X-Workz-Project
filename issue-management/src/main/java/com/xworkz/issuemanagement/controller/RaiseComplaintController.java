package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.RaiseComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@SessionAttributes("signUpDTO")
public class RaiseComplaintController {

    @Autowired
    private RaiseComplaintService raiseComplaintService;

    @Autowired
    private HttpSession httpSession;

    public RaiseComplaintController() {
        log.info("No argument constructor for RaiseComplaintController...");
    }

    @PostMapping("raise-complaint")
    public String raiseComplaint(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO, @ModelAttribute("raiseComplaintDTO") RaiseComplaintDTO raiseComplaintDTO, RedirectAttributes redirectAttributes, Model model) {
        log.info("raiseComplaint method running in RaiseComplaintController..");

        //Accessing from signup dto
        int signedUserId = signUpDTO.getId();
        System.out.println("Signed in user id:" + signedUserId);

        //set the signed in user id in raiseComplaintDTO
        SignUpDTO userDTO = new SignUpDTO();
        userDTO.setId(signedUserId);
        raiseComplaintDTO.setSignUpDTO(userDTO);

        boolean dataValid = raiseComplaintService.saveRaiseComplaintData(raiseComplaintDTO);

        if (dataValid) {
            log.info("RaiseComplaintService registration successful in RaiseComplaintController.");
            redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration Successful: " + raiseComplaintDTO.getComplaintId());
        } else {
            log.info("RaiseComplaintService registration not successful in RaiseComplaintController..");
            redirectAttributes.addFlashAttribute("raiseComplaintMsg", "Duplicate complaint detected. Registration failed.");
        }

        return "redirect:/raise-complaint";
    }

    @GetMapping("raise-complaint")
    public String showRaiseComplaintPage(Model model) {
        return "RaiseComplaint";
    }


    //view RaiseComplaint
    @GetMapping("view-raise-complaint")
    public String viewRaiseComplaint(Model model, @ModelAttribute("signUpDTO") SignUpDTO signUpDTO) {
        int userId = signUpDTO.getId();
        List<RaiseComplaintDTO> complaints = raiseComplaintService.getComplaintsByUserId(userId);
        model.addAttribute("viewRaiseComplaints", complaints);
        return "ViewRaiseComplaint";
    }


    //Edit Raise Complaint

//    @GetMapping("edit-complaint/{complaintId}")
//    public String editComplaint(@PathVariable("complaintId") int complaintId, Model model) {
//        RaiseComplaintDTO complaint = raiseComplaintService.getComplaintById(complaintId);
//        model.addAttribute("complaint", complaint);
//        return "EditComplaint";
//    }


//    @GetMapping("edit-complaint/{complaintId}")
//    public String editComplaint(@PathVariable("complaintId") int complaintId, Model model) {
//        RaiseComplaintDTO complaint = raiseComplaintService.getComplaintById(complaintId);
//        if (complaint == null) {
//            model.addAttribute("errorMsg", "Complaint not found");
//            model.addAttribute("raiseComplaintDTO", complaint); //value retain page
//
//            return "EditComplaint";
//        }
//        model.addAttribute("complaint", complaint);
//        return "EditRaiseComplaint";
//    }


//edit Raise_Complaint

    @GetMapping("/edit-complaint/{complaintId}")
    public String showEditComplaintForm(@PathVariable("complaintId") int complaintId, Model model) {
        RaiseComplaintDTO raiseComplaintDTO = raiseComplaintService.getComplaintById(complaintId);
        model.addAttribute("raiseComplaintDTO", raiseComplaintDTO);//values should be retain in page
        return "EditRaiseComplaint";
    }



    //update

    @PostMapping("update-complaint-detailes")
    public String updateComplaint(@ModelAttribute("raiseComplaintDTO") RaiseComplaintDTO raiseComplaintDTO, Model model) {
        System.out.println("RaiseComplaintDTO"+raiseComplaintDTO);
        List<RaiseComplaintDTO> isUpdated = raiseComplaintService.updateRaiseComplaintUserDetails(raiseComplaintDTO);
        if (isUpdated!=null) {
            model.addAttribute("updateMsg", "Complaint updated successfully!");
            model.addAttribute("viewRaiseComplaints", isUpdated); //for table data view raise complaint
            return "ViewRaiseComplaint";
        } else {
            model.addAttribute("updateErrorMsg", "Failed to update complaint. Please try again.");
        }
        return "EditRaiseComplaint";
    }




}




























