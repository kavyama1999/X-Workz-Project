package com.xworkz.issuemanagement.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController() {
        System.out.println("No parameter constructor created for AdminController...");
    }

    @PostMapping("admin")
    public String adminDetails(AdminDTO adminDTO, @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, Model model) {
        System.out.println("adminDetails method in AdminController");


        boolean data = adminService.findByEmailAndPassword(email, password);

        if (data) {
            System.out.println("findByEmailAndPassword successful in AdminController..");
            redirectAttributes.addFlashAttribute("adminMessage", "Login successful");

            model.addAttribute("AdminProfilePageMessage", "Welcome to Admin profile");
            return "AdminProfilePage";
            //return "redirect:/adminPage";
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminController");
            redirectAttributes.addFlashAttribute("errorAdminMessage", "Failed to login. Please check your email and password.");
            return "redirect:/adminPage";
        }
    }


    @GetMapping("adminPage")
    public String showAdminPage() {
        return "AdminPage";
    }


    //view user details(SignUp details)
    @GetMapping("view-user-details")
    public String viewUserDetails(SignUpDTO signUpDTO, Model model) {
        System.out.println("viewUserDetails method in AdminController..");
        List<SignUpDTO> signUpDtoData = adminService.findById(signUpDTO);

        if (signUpDtoData != null) {
            System.out.println("view-user-details successful in AdminController..");
            model.addAttribute("ViewUserDetails", signUpDtoData);
            return "AdminViewUserDetails";


        } else {
            System.out.println("view-user-details not  successful in AdminController..");
        }
        return "AdminViewUserDetails";
    }


    //view Raise complaint details
    @GetMapping("View-raise-complaint")
    public String viewRaiseComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model) {
        System.out.println("viewUserDetails method running in AdminController");

        List<RaiseComplaintDTO> viewData = adminService.findById(raiseComplaintDTO);
        model.addAttribute("viewRaiseComplaint", viewData);
        if (viewData != null) {
            System.out.println("View raise complaint data successful in AdminController");
            return "AdminViewRaiseComplaintDetails";
        } else {
            System.out.println("View raise complaint data not successful in AdminController.");
        }
        return "AdminViewRaiseComplaintDetails";
    }


//    //search by complaint  type
//
//    @PostMapping("ComplaintTypeSearch")
//    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//
//        System.out.println("searchByComplaintType method running  in   AdminController..");
//        List<RaiseComplaintDTO> data = adminService.searchByComplaintType(raiseComplaintDTO);
//        List<RaiseComplaintDTO> cityType= adminService.searchComplaintByCity(raiseComplaintDTO);
//        model.addAttribute("complaintType", data);
//
//        if (data != null) {
//            System.out.println("searchByComplaintType successful in AdminController..");
//            return "SearchRaiseComplaint";
//
//        } else {
//            System.out.println("searchByComplaintType not successful in AdminController.. ");
//        }
//
//        if(cityType!=null)
//        {
//            System.out.println("searchComplaintByCity successful in AdminController");
//            model.addAttribute("complaintType", cityType);
//
//            return "SearchRaiseComplaint";
//        }
//
//        else
//        {
//            System.out.println("searchComplaintByCity not successful in AdminController");
//        }
//
//        return "SearchRaiseComplaint";
//    }


    @PostMapping("ComplaintTypeSearch")
    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
        System.out.println("searchByComplaintType method running in AdminController..");

        List<RaiseComplaintDTO> data = adminService.searchByComplaintType(raiseComplaintDTO);
        List<RaiseComplaintDTO> cityType = adminService.searchComplaintByCity(raiseComplaintDTO);

        // Combine both lists to ensure all relevant data is included
        List<RaiseComplaintDTO> combinedData = new ArrayList<>();
        if (data != null) {
            combinedData.addAll(data);
        }
        if (cityType != null) {
            combinedData.addAll(cityType);
        }

        // Add combined data to the model
        model.addAttribute("complaintType", combinedData);

        if (combinedData.isEmpty()) {
            System.out.println("No data found for the given criteria in AdminController.");
        } else {
            System.out.println("Data found for the given criteria in AdminController.");
        }

        return "SearchRaiseComplaint";
    }

}

//    //search by complaint city
//    @PostMapping("ComplaintTypeSearch")
//    public String searchByComplaintCity(RaiseComplaintDTO raiseComplaintDTO, Model Model) {
//
//        System.out.println("searchByComplaintCity method running in AdminController..");
//        List<RaiseComplaintDTO> cityData = adminService.searchComplaintByCity(raiseComplaintDTO);
//        if (cityData != null) {
//            System.out.println("searchComplaintByCity successful in AdminController");
//            return "SearchRaiseComplaint";
//        }
//        else
//        {
//            System.out.println("searchComplaintByCity not successful in AdminController");
//        }
//
//        return "SearchRaiseComplaint";
//    }


