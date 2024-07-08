//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//@Controller
//@SessionAttributes("signUpDTO")
//public class ProfileController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/profile")
//    public String showProfile(Model model) {
//        // Assuming you have a method to get the currently logged-in user's email
//        String userEmail = userService.getLoggedInUserEmail();
//
//        // Fetch user data based on the email
//        SignUpDTO signUpDTO = userService.getUserByEmail(userEmail);
//
//        // Add the user data to the model
//        model.addAttribute("signUpDTO", signUpDTO);
//
//        // Return the view name
//        return "profile";
//    }
//}


//EntityManager entityManager = entityManagerFactory.createEntityManager();
//String query = "SELECT e FROM SignUpDTO e WHERE e.email = :email";
//Query query1 = entityManager.createQuery(query);
//        query1.setParameter("email", email);
//
//SignUpDTO signUpDTO = (SignUpDTO) query1.getSingleResult();
//        System.out.println(signUpDTO);
//
//        return signUpDTO;