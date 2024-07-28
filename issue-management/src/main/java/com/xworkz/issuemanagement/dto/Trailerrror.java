//@PostMapping("/allocate-department")
//public String allocateDepartment(
//        @RequestParam("complaintId") Long complaintId,
//        @RequestParam("departmentId") Long departmentId,
//        @RequestParam("status") String status,
//        Model model
//) {
//    try {
//        System.out.println("Running allocate department");
//
//        // Call the service method to allocate department
//        adminService.allocateDepartment(complaintId, departmentId,status);
//        System.out.println("complaintId"+complaintId);
//        System.out.println("departmentId"+departmentId);
//        model.addAttribute("successMessage", "Department allocated successfully!");
//    } catch (Exception e) {
//        model.addAttribute("errorMessage", "Failed to allocate department. Please try again.");
//        e.printStackTrace();
//    }
//    return "AdminViewUserComplaintDetails";
//
//}