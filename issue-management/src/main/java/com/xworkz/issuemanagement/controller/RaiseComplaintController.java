package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.RaiseComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/")
public class RaiseComplaintController {

    @Autowired
    private RaiseComplaintService raiseComplaintService;

    public RaiseComplaintController() {
        log.info("No argument constructor for RaiseComplaintController...");
    }

    @PostMapping("raise-complaint")
    public String raiseComplaint(RaiseComplaintDTO raiseComplaintDTO, RedirectAttributes redirectAttributes, SignUpDTO sig) {
        log.info("raiseComplaint method running in RaiseComplaintController..");


        boolean dataValid = raiseComplaintService.saveRaiseComplaintData(raiseComplaintDTO);

        if (dataValid) {
            log.info("RaiseComplaintService registration successful in RaiseComplaintController.");
            redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration Successful: " + raiseComplaintDTO.getComplaintId());
        } else {
            log.info("RaiseComplaintService registration not successful in RaiseComplaintController..");
            redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration failed");
        }


        return "redirect:/raise-complaint";
    }

    @GetMapping("raise-complaint")
    public String showRaiseComplaintPage(Model model) {
        return "RaiseComplaint";
    }
}


//    @GetMapping("find-complaint")
//    public String findComplaintByUserId(@RequestParam("userId") int userId, Model model) {
//        log.info("findComplaintByUserId method running in RaiseComplaintController..");
//
//        RaiseComplaintDTO complaint = raiseComplaintService.findByUserId(userId);
//
//        if (complaint != null) {
//            log.info("Complaint found for user ID: {}", userId);
//            model.addAttribute("complaint", complaint);
//        } else {
//            log.info("No complaint found for user ID: {}", userId);
//            model.addAttribute("error", "No complaint found for user ID: " + userId);
//        }
//
//        return "ViewComplaint";
//    }

