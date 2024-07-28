package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String viewRaiseComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model, DepartmentDTO departmentDTO) {
        System.out.println("viewUserDetails method running in AdminController");

        List<RaiseComplaintDTO> viewData = adminService.findById(raiseComplaintDTO);

        // Fetch the list of complaints and departments
        List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());

        model.addAttribute("viewRaiseComplaint", viewData);
        model.addAttribute("departments", departments);// Fetch the list of complaints and departments

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
            model.addAttribute("viewRaiseComplaint", listOfTypeAndCity);
            return "AdminViewRaiseComplaintDetails";
        } else {
            List<RaiseComplaintDTO> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());

            //System.out.println("TypeOrCity : " + listOfTypeOrCity);
            if (!listOfTypeOrCity.isEmpty()) {
                //   System.out.println("searchByComplaintTypeOrCity ");
                model.addAttribute("viewRaiseComplaint", listOfTypeOrCity);
                return "AdminViewRaiseComplaintDetails";

            }
        }

        return "AdminViewRaiseComplaintDetails";
    }


    //save  department

    @PostMapping("add-department")
    public String saveDepartment(DepartmentDTO departmentDTO, Model model) {
        System.out.println("saveDepartment method running in AdminController..");

        DepartmentDTO data = adminService.saveDepartment(departmentDTO);
        List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());


        if (data != null) {
            System.out.println("saveDepartment successful in AdminController..");
            model.addAttribute("msg", "Successfully added department ");
//          return  "AdminAddComplaints";
            return "redirect:/add-departments";

        } else {
            System.out.println("saveDepartment not successful in AdminController..");

            model.addAttribute("error", "not Successfully added department");
        }

        //return "AdminAddComplaints";
        return "redirect:/add-departments";
    }


    //add departments
    @GetMapping("add-departments")
    public String save(DepartmentDTO departmentDTO, Model model)
    {
        // Retrieve and add the list of departments to the model
      // List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentType());
       // model.addAttribute("departments", departments);
        model.addAttribute("msg", "Successfully added department ");


        return "AdminAddComplaints";
    }

    //update department id  and status

    @PostMapping("/update-department")
    public String updateComplaint(@RequestParam("complaintId") int complaintId,
                                  @RequestParam("departmentId") int departmentId,
                                  @RequestParam("status") String status,
                                  Model model) {
        adminService.updateStatusAndDepartmentId(complaintId, departmentId, status);
        model.addAttribute("successMessage", "Department allocated successfully!");
        return "AdminViewRaiseComplaintDetails";
    }

}



