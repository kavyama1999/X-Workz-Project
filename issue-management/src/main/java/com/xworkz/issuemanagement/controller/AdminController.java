package com.xworkz.issuemanagement.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.model.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.taglibs.standard.extra.spath.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
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

        // Fetch the list of  and departments
        List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());

        model.addAttribute("viewRaiseComplaint", viewData);
        model.addAttribute("departments", departments);// Fetch the list of  and departments(complaints)

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
//    @PostMapping("/ComplaintTypeSearch")
//    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//
//        System.out.println("searchByComplaintType method running in AdminController..!!");
//
//
//        List<RaiseComplaintDTO> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
//
//        //  System.out.println("TypeAndCity : " + listOfTypeAndCity);
//
//        if (!listOfTypeAndCity.isEmpty()) {
//            // System.out.println("searchByComplaintTypeAndCity successful in AdminController");
//            model.addAttribute("viewRaiseComplaint", listOfTypeAndCity);
//            return "AdminViewRaiseComplaintDetails";
//        } else {
//            List<RaiseComplaintDTO> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
//
//            //System.out.println("TypeOrCity : " + listOfTypeOrCity);
//            if (!listOfTypeOrCity.isEmpty()) {
//                //   System.out.println("searchByComplaintTypeOrCity ");
//                model.addAttribute("viewRaiseComplaint", listOfTypeOrCity);
//                return "AdminViewRaiseComplaintDetails";
//
//            }
//        }
//
//        return "AdminViewRaiseComplaintDetails";
//    }


    //****************************************************


    @PostMapping("/ComplaintTypeSearch")
    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, DepartmentDTO departmentDTO, Model model) {
        List<RaiseComplaintDTO> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
        if (!listOfTypeAndCity.isEmpty()) {

            List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());

            model.addAttribute("viewRaiseComplaint", listOfTypeAndCity);
            model.addAttribute("departments", departments);
            return "AdminViewRaiseComplaintDetails";
        }

        List<RaiseComplaintDTO> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
        if (!listOfTypeOrCity.isEmpty()) {

            // model.addAttribute("departments", departmentIds);


            List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());
            model.addAttribute("viewRaiseComplaint", listOfTypeOrCity);
            model.addAttribute("departments", departments); //retain
            return "AdminViewRaiseComplaintDetails";
        }

        return "AdminViewRaiseComplaintDetails";
    }

//**************************************************************//


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


    @GetMapping("add-departments")
    public String save(DepartmentDTO departmentDTO, Model model) {
        // Retrieve and add the list of departments to the model
        // List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentType());
        // model.addAttribute("departments", departments);
        model.addAttribute("msg", "Successfully added department ");


        return "AdminAddComplaints";
    }


    //*********************************************************************//

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


    //****************************************************
    //Add Department Admin and saved in database(register page)


    @PostMapping("add-department-admin")
    public String addDepartmentAdmin(@Valid RegisterDepartmentAdminDTO registerDepartmentAdminDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("addDepartmentAdmin method running in AdminController..");
        System.out.println("RegisterDepartmentAdminDTO  : " + registerDepartmentAdminDTO);

        // Fetch and add the department list to the model
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments", departments);

        if (bindingResult.hasErrors()) {
            System.out.println("RegisterDepartmentAdminDTO has invalid data");
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("registerDepartmentAdminDTO", registerDepartmentAdminDTO); // Retain form values

            return "RegisterDepartmentAdmin"; // Return directly to the form page with errors
        } else {
            // Fetch the department by name and to save departmentId in department table
            DepartmentDTO departmentDTO = adminService.findDepartmentByName(registerDepartmentAdminDTO.getDepartmentName());

            System.out.println(registerDepartmentAdminDTO.getDepartmentName());
            System.out.println(departmentDTO);
            if (departmentDTO != null) {
                registerDepartmentAdminDTO.setDepartmentId(departmentDTO); //to save departmentId in departmnet table

                boolean saveData = adminService.saveDepartmentAdminData(registerDepartmentAdminDTO);
                if (saveData) {
                    System.out.println("saveDepartmentAdminData saved successfully in addDepartmentAdmin");
                    redirectAttributes.addFlashAttribute("msg", "Department Admin data saved successfully.");
                    return "redirect:/departmentAdmin";
                } else {
                    System.out.println("saveDepartmentAdminData not saved successfully.");
                    redirectAttributes.addFlashAttribute("errorMsg", "Department Admin data not saved successfully.");
                }
            }
            else {
                System.out.println("Department not found.");
                redirectAttributes.addFlashAttribute("errorMsg", "Invalid Department Name.");
            }

            return "redirect:/departmentAdmin";
        }
    }

    @GetMapping("departmentAdmin")
    public String departmentAdmin(Model model) {
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "RegisterDepartmentAdmin";
    }





    //********************************************************************************//
    //sub admin login
    @PostMapping("sub-admin-log-in")
    public String subAdminLogin(RegisterDepartmentAdminDTO registerDepartmentAdminDTO, @RequestParam("email") String email,
                                @RequestParam("password") String password, @RequestParam("departmentName") String departmentName, Model model, RedirectAttributes redirectAttributes,
                                HttpServletRequest httpServletRequest) {

        System.out.println("subAdminLogin method running in AdminController..");
        RegisterDepartmentAdminDTO login = adminService.findEmailAndPassword(email, password,departmentName);

        if (login != null) {
            log.info("subAdminLogin successful AdminController..");

            adminService.resetFailedAttempts(email); //locked

            redirectAttributes.addFlashAttribute("msg", "Login successful");
            model.addAttribute("DepartmentAdminProfileMsg", "Sub Admin Login successful ");


            //session


            HttpSession session=httpServletRequest.getSession();



            session.setAttribute("departmentAdmin",login.getDepartmentName());//session key




            //return "DepartmentAdminLoginPage";
            return "DepartmentAdminProfilePage";

        } else {
            log.info("subAdminLogin not successful in AdminController..");
            adminService.incrementFailedAttempts(email); //locked

            int failedAttempts = adminService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + " : " + failedAttempts);
            model.addAttribute("errorMsg", "Failed to login please check your email and password");

            if (failedAttempts >= 3) {
                adminService.lockAccount(email);// Lock account after 3 failed attempts
                System.out.println(email + " :Your account is locked due to too many failed attempts");

                redirectAttributes.addFlashAttribute("accountError", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);

            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid email id and password. Attempts:" + failedAttempts);
                System.out.println("Invalid email Id and password");
                model.addAttribute("accountLocked", false);
            }

            return "redirect:/departmentAdminLogIn";
            // return "DepartmentAdminProfilePage";

        }
    }

    @GetMapping("departmentAdminLogIn")
    public String departmentAdminLogIn(Model model) {
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "DepartmentAdminLoginPage";
    }



    //*****************************************************************

    //forgot password


    @PostMapping("sub-admin-forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {

        log.info("forgotPassword method running in AdminController..");

        RegisterDepartmentAdminDTO resetEmail = adminService.resetPasswordEmail(email);

        if (resetEmail != null) {
            log.info("forgotPassword successful in AdminController..");
            model.addAttribute("msg", "A new password has been sent to your email.");

            // Reset failed attempts
            adminService.resetFailedAttempts(email);
            adminService.unlockAccount(email);

            return "DepartmentAdminForgotPassword";
        } else {
            log.info("forgotPassword not successful in AdminController..");
            model.addAttribute("errorMsg", "Email Address not found");
        }

        return "DepartmentAdminForgotPassword";
    }


    //*******************************************************************
    //sub Admin change password

    @PostMapping("change-password")
    public String subAdminChangePassword(Model model, @RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword) {

        System.out.println("email"+email+"old"+oldPassword+"new"+newPassword+"con"+confirmPassword);

        log.info("subAdminChangePassword method running in AdminController..");

        boolean changePasswordSuccessful = adminService.changePassword(email, oldPassword, newPassword, confirmPassword);
        if (changePasswordSuccessful) {
            System.out.println("Change password Successful:" + changePasswordSuccessful);
            model.addAttribute("DepartmentAdminChangePasswordMessage" + "", "Change Password  successful");

            return "DepartmentAdminChangePassword";

        } else {
            model.addAttribute("DepartmentAdminChangePasswordError", "Failed to change password.Please check your password and email");
//           // return "SubAdminChangePassword";
        }

        // Retain email to display in the form again
        model.addAttribute("email", email);
        return "DepartmentAdminChangePassword";

    }



    //**************************************************************************

    //department admin can view particular department raise complaint details




    @GetMapping("department-admin-view-particular-department")
    public String  getParticularDepartment(RaiseComplaintDTO raiseComplaintDTO,Model model,
                                           HttpServletRequest httpServletRequest)
    {
      log.info("getParticularDepartment method running in AdminController...");

      //session

        HttpSession session=httpServletRequest.getSession();
        String  departmentDTO= (String) session.getAttribute("departmentAdmin");

    List<RaiseComplaintDTO> getData=  adminService.getParticularDepartments(departmentDTO);

    if(getData!=null)
    {
        log.info("getParticularDepartment data successful in AdminController");
        model.addAttribute("particularDepartment",getData);
        return "DepartmentAdminViewComplaintRaiseDetails";
    }

    else
    {
        log.info("getParticularDepartment data not successful in AdminController..");
    }

    return "DepartmentAdminViewComplaintRaiseDetails";
    }
}



