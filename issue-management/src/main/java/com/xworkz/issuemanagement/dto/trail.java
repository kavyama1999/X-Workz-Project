//// Add this import at the top of your file
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    public AdminController() {
//        System.out.println("No parameter constructor created for AdminController...");
//    }
//
//    @GetMapping("adminPage")
//    public String showAdminPage() {
//        return "AdminPage";
//    }
//
//    @GetMapping("view-user-details")
//    public String viewUserDetails(SignUpDTO signUpDTO, Model model) {
//        List<SignUpDTO> signUpDtoData = adminService.findById(signUpDTO);
//        model.addAttribute("ViewUserDetails", signUpDtoData);
//        return "AdminViewUserDetails";
//    }
//
//    @GetMapping("View-raise-complaint")
//    public String viewRaiseComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model, DepartmentDTO departmentDTO) {
//        List<RaiseComplaintDTO> viewData = adminService.findById(raiseComplaintDTO);
//        List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());
//        model.addAttribute("viewRaiseComplaint", viewData);
//        model.addAttribute("departments", departments);
//        return "AdminViewRaiseComplaintDetails";
//    }
//
//    @PostMapping("/ComplaintTypeSearch")
//    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//        List<RaiseComplaintDTO> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
//        if (!listOfTypeAndCity.isEmpty()) {
//            List<Integer> departmentIds = listOfTypeAndCity.stream()
//                    .filter(complaint -> complaint.getDepartmentDTO() != null)
//                    .map(complaint -> complaint.getDepartmentDTO().getId())
//                    .collect(Collectors.toList());
//
//            List<DepartmentDTO> departments = adminService.findAll(departmentIds);
//            model.addAttribute("viewRaiseComplaint", listOfTypeAndCity);
//            model.addAttribute("departments", departments);
//            return "AdminViewRaiseComplaintDetails";
//        }
//
//        List<RaiseComplaintDTO> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
//        if (!listOfTypeOrCity.isEmpty()) {
//            List<Integer> departmentIds = listOfTypeOrCity.stream()
//                    .filter(complaint -> complaint.getDepartmentDTO() != null)
//                    .map(complaint -> complaint.getDepartmentDTO().getId())
//                    .collect(Collectors.toList());
//
//            List<DepartmentDTO> departments = adminService.findAll(departmentIds);
//            model.addAttribute("viewRaiseComplaint", listOfTypeOrCity);
//            model.addAttribute("departments", departments);
//            return "AdminViewRaiseComplaintDetails";
//        }
//
//        return "AdminViewRaiseComplaintDetails";
//    }
//
//    @PostMapping("add-department")
//    public String saveDepartment(DepartmentDTO departmentDTO, Model model) {
//        adminService.saveDepartment(departmentDTO);
//        List<DepartmentDTO> departments = adminService.findAll(departmentDTO.getDepartmentName());
//        model.addAttribute("msg", "Successfully added department");
//        return "redirect:/add-departments";
//    }
//
//    @GetMapping("add-departments")
//    public String save(DepartmentDTO departmentDTO, Model model) {
//        model.addAttribute("msg", "Successfully added department");
//        return "AdminAddComplaints";
//    }
//
//    @PostMapping("/update-department")
//    public String updateComplaint(@RequestParam("complaintId") int complaintId,
//                                  @RequestParam("departmentId") int departmentId,
//                                  @RequestParam("status") String status,
//                                  Model model) {
//        adminService.updateStatusAndDepartmentId(complaintId, departmentId, status);
//        return "redirect:/View-raise-complaint";
//    }
//}


//@Override
//public RegisterDepartmentAdminDTO findEmailAndPassword(String email, String password, String departmentName) {
//    log.info("findEmailAndPassword method running in AdminServiceImpl..");
//
//    // Retrieve the RegisterDepartmentAdminDTO by email
//    RegisterDepartmentAdminDTO registerDepartmentAdminDTO = adminRepo.findByEmail(email);
//
//    // Check if the retrieved object is null
//    if (registerDepartmentAdminDTO != null) {
//        // Compare passwords and department name
//        if (passwordEncoder.matches(password, registerDepartmentAdminDTO.getPassword())
//                && registerDepartmentAdminDTO.getDepartmentName().equals(departmentName)) {
//            log.info("findEmailAndPassword successful in AdminServiceImpl..");
//            return registerDepartmentAdminDTO;
//        } else {
//            log.info("findEmailAndPassword not successful in AdminServiceImpl..");
//            return null;
//        }
//    } else {
//        // Log that the email was not found
//        log.info("No account found with email: " + email);
//        return null;
//    }
//}
