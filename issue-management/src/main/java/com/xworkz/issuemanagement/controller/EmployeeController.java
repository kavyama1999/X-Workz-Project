package com.xworkz.issuemanagement.controller;


import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private AdminService adminService;


    public EmployeeController() {
        log.info("no parameter constructor in EmployeeController ");
    }


    @PostMapping("employeeData")
    public String saveEmployeeData(EmployeeDTO employeeDTO, Model model, RedirectAttributes redirectAttributes) {
        log.info("saveEmployeeData method running in EmployeeController..");
//     boolean saveEmployeeData = employeeService.saveEmployeeData(employeeDTO);

        // Fetch the department by name and to save departmentId in department table

        DepartmentDTO departmentDTO = adminService.findDepartmentByName(employeeDTO.getDepartmentName());

        System.out.println(employeeDTO.getDepartmentName());
        System.out.println("departmentDTO od department name :" +departmentDTO);


        if(departmentDTO!=null) {
            employeeDTO.setDepartmentId(departmentDTO);

            boolean saveEmployeeData = employeeService.saveEmployeeData(employeeDTO);

            if (saveEmployeeData) {
                log.info("saveEmployeeData saved successfully in EmployeeController..");
                redirectAttributes.addFlashAttribute("msg", "Employee data saved successfully..");
                return "redirect:/employeeRegister";
            } else {
                log.info("saveEmployeeData not saved successfully in EmployeeController..");
                redirectAttributes.addFlashAttribute("errorMsg", "Failed to save Employee data");
            }
        }
        return "redirect:/employeeRegister";
    }


    @GetMapping("employeeRegister")   //for load department we have give this action into href link
    public String departmentAdminLogIn(Model model) {
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "EmployeeRegisterPage";
    }
}
