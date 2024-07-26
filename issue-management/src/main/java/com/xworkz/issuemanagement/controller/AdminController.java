package com.xworkz.issuemanagement.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.ComplaintDepartmentDTO;
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


////**************************************************************////////


    // Combined search endpoint for both OR and AND conditions
    @PostMapping("/ComplaintTypeSearch")
    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {

        System.out.println("searchByComplaintType method running in AdminController..!!");



        List<RaiseComplaintDTO> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());

      //  System.out.println("TypeAndCity : " + listOfTypeAndCity);

        if (!listOfTypeAndCity.isEmpty()) {
            // System.out.println("searchByComplaintTypeAndCity successful in AdminController");
            model.addAttribute("com", listOfTypeAndCity);
            return "SearchRaiseComplaint";
        } else {
            List<RaiseComplaintDTO> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());

            //System.out.println("TypeOrCity : " + listOfTypeOrCity);
            if (!listOfTypeOrCity.isEmpty()) {
                //   System.out.println("searchByComplaintTypeOrCity ");
                model.addAttribute("com", listOfTypeOrCity);
                return "SearchRaiseComplaint";

            }
        }

        return "SearchRaiseComplaint";
    }



    //save  department

    @PostMapping("add-department")
    public String saveDepartment(ComplaintDepartmentDTO complaintDepartmentDTO, Model model)
    {
        System.out.println("saveDepartment method running in AdminController..");

      ComplaintDepartmentDTO data=  adminService.saveDepartment(complaintDepartmentDTO);
      if(data!=null)
      {
          System.out.println("saveDepartment successful in AdminController..");
          model.addAttribute("msg","Successfully added department ");
//          return  "AdminAddComplaints";
          return "redirect:/add-departments";

      }

      else
      {
          System.out.println("saveDepartment not successful in AdminController..");
          model.addAttribute("error","not Successfully added department");
      }

      //return "AdminAddComplaints";
        return "redirect:/add-departments";
    }


    @GetMapping("add-departments")
    public String  save(ComplaintDepartmentDTO complaintDepartmentDTO,Model model)
    {
        model.addAttribute("msg","Successfully added department ");

        return "AdminAddComplaints";
    }
}
