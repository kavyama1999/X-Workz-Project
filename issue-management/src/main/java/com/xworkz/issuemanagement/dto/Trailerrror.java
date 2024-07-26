//@PostMapping("/search-user-complaints")
//public String searchUserComplaintDetails(RaiseComplaintDto raiseComplaintDto, Model model) {
//    System.out.println("viewUserDetails method in AdminController..");
//
//    List<RaiseComplaintDto> listOfComplaintTypeAndCity = adminService.searchByUserComplaintTypeAndCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
//    if (!listOfComplaintTypeAndCity.isEmpty()) {
//        model.addAttribute("listOfComplaintType", listOfComplaintTypeAndCity);
//        return "AdminViewUserComplaintsSearchDetails";
//    }
//    else {
//        List<RaiseComplaintDto> listOfComplaintTypeOrCity = adminService.searchByUserComplaintTypeOrCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
//        if (!listOfComplaintTypeOrCity.isEmpty()) {
//            model.addAttribute("listOfComplaintType", listOfComplaintTypeOrCity);
//            return "AdminViewUserComplaintsSearchDetails";
//        }
//    }
//
//    return "AdminViewUserComplaintsSearchDetails";
//}


//@PostMapping("/search-user-complaints")
//public String searchUserComplaintDetails(RaiseComplaintDto raiseComplaintDto, Model model) {
//    System.out.println("viewUserDetails method in AdminController..");
//
//    List<RaiseComplaintDto> listOfComplaintTypeAndCity = adminService.searchByUserComplaintTypeAndCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
//    if (!listOfComplaintTypeAndCity.isEmpty()) {
//        model.addAttribute("listOfComplaintType", listOfComplaintTypeAndCity);
//        return "AdminViewUserComplaintsSearchDetails";
//    }
//    else {
//        List<RaiseComplaintDto> listOfComplaintTypeOrCity = adminService.searchByUserComplaintTypeOrCity(raiseComplaintDto.getComplaintType(), raiseComplaintDto.getCity());
//        if (!listOfComplaintTypeOrCity.isEmpty()) {
//            model.addAttribute("listOfComplaintType", listOfComplaintTypeOrCity);
//            return "AdminViewUserComplaintsSearchDetails";
//        }
//    }
//
//    return "AdminViewUserComplaintsSearchDetails";
//}
//}