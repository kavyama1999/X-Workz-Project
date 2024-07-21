////////package com.xworkz.xworkzProject.controller;
////////
////////import com.xworkz.xworkzProject.dto.RaiseComplaintDto;
////////import com.xworkz.xworkzProject.dto.SignupDto;
////////import com.xworkz.xworkzProject.model.service.RaiseComplaintService;
////////import org.springframework.beans.factory.annotation.Autowired;
////////import org.springframework.stereotype.Controller;
////////import org.springframework.ui.Model;
////////import org.springframework.web.bind.annotation.*;
////////
////////import javax.jws.WebParam;
////////import javax.servlet.http.HttpServletRequest;
////////import javax.servlet.http.HttpSession;
////////import java.util.Optional;
////////
////////@Controller
////////@RequestMapping("/")
////////@SessionAttributes("signupDto")
////////public class RaiseComplaintController {
////////
////////    @Autowired
////////    private RaiseComplaintService raiseComplaintService;
////////
////////    public RaiseComplaintController()
////////    {
////////        System.out.println("Created RaiseComplaintController");
////////    }
////////
////////    @PostMapping("/raise-complaint")
////////    public String raiseComplaint(@ModelAttribute("signupDto") SignupDto signupDto,@ModelAttribute("raiseComplaintDto") RaiseComplaintDto raiseComplaintDto, Model model)
////////    {
////////        System.out.println("Running raiseComplaint method in RaiseComplaintController...");
////////        // Accessing id from SignupDto
////////        int signedInUserId = signupDto.getId();
////////        System.out.println("Signed in user ID: " + signedInUserId);
////////
////////        // Set the signed in user ID in raiseComplaintDto
////////        SignupDto userDto = new SignupDto();
////////        userDto.setId(signedInUserId);
////////        raiseComplaintDto.setUserId(userDto);
////////
////////        boolean save=raiseComplaintService.saveRaiseComplaintType(raiseComplaintDto);
////////
////////
////////        if(save)
////////        {
////////            System.out.println("Controller:save raiseComplaint details successfully"+raiseComplaintDto);
////////            model.addAttribute("raiseComplaintSucess","saved raiseComplaint details successfully");
////////            return "ViewComplaint";
////////        }
////////
////////        else {
////////            model.addAttribute("ErrorRaiseComplaintSucess"," Not saved raiseComplaint details successfully");
////////            System.out.println("Controller:not save raiseComplaint details successfully"+raiseComplaintDto);
////////        }
////////        return "RaiseComplaint";
////////    }
////////
////////    @GetMapping("view-complaint")
////////    public String viewComplaint(@RequestParam("complaintId")int complaintId,HttpServletRequest request, Model model) {
////////        System.out.println("Running viewComplaint method in RaiseComplaintController...");
////////
////////        // Step 1: Retrieve signed-in user email
////////        HttpSession httpSession = request.getSession();
////////        RaiseComplaintDto raiseComplaintDto = (RaiseComplaintDto) httpSession.getAttribute("raiseComplaintDto");
////////        Integer cid = raiseComplaintDto != null ? raiseComplaintDto.getComplaintId() : null;
////////
////////        // Step 2: Retrieve complaint DTO based on complaint ID (assuming complaintId is obtained somehow)
////////        //int complaintId = raiseComplaintDto.getComplaintId(); // You need to define how you get the complaintId
////////        RaiseComplaintDto raiseComplaintDto1 = raiseComplaintService.findByComplaintId(complaintId);
////////
////////        // Step 3: Add the complaint DTO to the model
////////        model.addAttribute("raiseComplaintDto", raiseComplaintDto);
////////
////////        // Step 4: Return the view name
////////        return "ViewComplaint"; // Assuming "ComplaintView" is your view name
////////    }
////////
////////    @GetMapping("edit-complaint")
////////    public String editComplaint(@RequestParam("complaintId")int complaintId)
////////    {
////////        System.out.println("Running editComplaint running in RaiseComplaintController ");
////////        RaiseComplaintDto raiseComplaintDto1 = raiseComplaintService.findByComplaintId(complaintId);
////////
////////        return "EditRaiseComplaint";
////////    }
////////
////////}
//////
//////
//////package com.xworkz.xworkzProject.model.repo;
//////
//////import com.xworkz.xworkzProject.dto.ImageUploadDto;
//////import com.xworkz.xworkzProject.dto.RaiseComplaintDto;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Repository;
//////
//////import javax.persistence.*;
//////import javax.transaction.Transactional;
//////import java.util.Optional;
//////
//////@Repository
//////public class RaiseComplaintRepoImpl implements RaiseComplaintRepo {
//////
//////    @Autowired
//////    private EntityManagerFactory entityManagerFactory;
//////
//////    public RaiseComplaintRepoImpl() {
//////        System.out.println("Created RaiseComplaintRepoImpl");
//////    }
//////
//////    @Override
//////    public boolean saveRaiseComplaintType(RaiseComplaintDto raiseComplaintDto) {
//////        System.out.println("Running saveRaiseComplaintType method in RaiseComplaintRepoImpl ");
//////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//////        EntityTransaction entityTransaction = entityManager.getTransaction();
//////        try {
//////            entityTransaction.begin();
//////            entityManager.persist(raiseComplaintDto);
//////            entityTransaction.commit();
//////        } catch (PersistenceException persistenceException) {
//////
//////            persistenceException.getStackTrace();
//////            entityTransaction.rollback();
//////        } finally {
//////            entityManager.close();
//////        }
//////        return true;
//////    }
//////
//////    @Override
//////    public Optional<RaiseComplaintDto> findByUserId(int id) {
//////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//////        try {
//////            TypedQuery<RaiseComplaintDto> query = entityManager.createQuery(
//////                    "SELECT i FROM RaiseComplaintDto i WHERE i.id = :id", RaiseComplaintDto.class);
//////            query.setParameter("id", id);
//////            return query.getResultList().stream().findFirst();
//////        } finally {
//////            entityManager.close();
//////        }
//////    }
//////
//////    @Override
//////    public RaiseComplaintDto findByComplaintId(int complaintId) {
//////        System.out.println("Running findByComplaintId method in RaiseComplaintRepoImpl");
//////        EntityManager entityManager=entityManagerFactory.createEntityManager();
//////        String query="Select c from RaiseComplaintDto c where c.complaintId=: complaintId";
//////        Query query1=entityManager.createQuery(query);
//////        query1.setParameter("complaintId",complaintId);
//////        RaiseComplaintDto raiseComplaintDto= (RaiseComplaintDto) query1.getSingleResult();
//////        return raiseComplaintDto;
//////    }
//////}
////
////
////
////
////
////
////
////
////v
//
//
//package com.xworkz.xworkzProject.model.service;
//
//import com.xworkz.xworkzProject.dto.RaiseComplaintDto;
//import com.xworkz.xworkzProject.dto.SignupDto;
//import com.xworkz.xworkzProject.model.repo.RaiseComplaintRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Optional;
//
//@Service
//public class RaiseComplaintServiceImpl implements RaiseComplaintService{
//
//    @Autowired
//    private RaiseComplaintRepo raiseComplaintRepo;
//
//    public RaiseComplaintServiceImpl()
//    {
//        System.out.println("Created RaiseComplaintServiceImpl");
//
//    }
//
//
//    @Override
//    public boolean saveRaiseComplaintType(RaiseComplaintDto raiseComplaintDto) {
//        System.out.println("Running saveRaiseComplaintType method in RaiseComplaintServiceImpl ");
//        boolean save=raiseComplaintRepo.saveRaiseComplaintType(raiseComplaintDto);
//        if(save)
//        {
//            System.out.println(" saved RaiseComplaint successfully ");
//        }
//        else {
//            System.out.println(" Not saved RaiseComplaint successfully ");
//        }
//        return true;
//    }
//
//
//    @Override
//    public Optional<RaiseComplaintDto> findByUserId(int id) {
//        return raiseComplaintRepo.findByUserId(id);
//    }
//
//    @Override
//    public Optional<RaiseComplaintDto> findBySignedInUser(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession();
//        SignupDto signedInUser = (SignupDto) httpSession.getAttribute("signupDto");
//        if (signedInUser != null) {
//            return raiseComplaintRepo.findByUserId(signedInUser.getId());
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public RaiseComplaintDto findByComplaintId(int complaintId) {
//        System.out.println("Running findByComplaintId method in RaiseComplaintServiceImpl... ");
//        return raiseComplaintRepo.findByComplaintId(complaintId);
//    }
//
//}