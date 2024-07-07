//package com.xworkz.issuemanagement.model.repository;
//
//import org.springframework.stereotype.Repository;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//@Repository
//public class PasswordResetRepoImpl implements PasswordResetRepo {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public boolean emailExists(String email) {
//        // Implementation to check if the email exists in the database
//        // Example:
//        Long count = (Long) entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email")
//                .setParameter("email", email)
//                .getSingleResult();
//        return count > 0;
//    }
//
//    @Override
//    public boolean verifyOldPassword(String email, String oldPassword) {
//        // Implementation to verify the old password
//        // Example:
//        Long count = (Long) entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email AND u.password = :password")
//                .setParameter("email", email)
//                .setParameter("password", oldPassword)
//                .getSingleResult();
//        return count > 0;
//    }
//
//    @Override
//    @Transactional
//    public void updatePassword(String email, String newPassword) {
//        // Implementation to update the password
//        // Example:
//        entityManager.createQuery("UPDATE User u SET u.password = :password WHERE u.email = :email")
//                .setParameter("password", newPassword)
//                .setParameter("email", email)
//                .executeUpdate();
//    }
//}
//////////////////

//package com.xworkz.issuemanagement.controller;

//import com.xworkz.issuemanagement.model.service.PasswordResetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//public class PasswordResetController {
//
//    @Autowired
//    private PasswordResetService passwordResetService;
//
//    public PasswordResetController() {
//        System.out.println("No param constructor created for PasswordResetController...");
//    }
//
//    @PostMapping("reset-password")
//    public String passwordReset(Model model, String email, String oldPassword, String newPassword, String confirmPassword) {
//        boolean resetSuccessful = passwordResetService.resetPassword(email, oldPassword, newPassword, confirmPassword);
//        if (resetSuccessful) {
//            model.addAttribute("passwordResetMessage", "Password reset successful");
//        } else {
//            model.addAttribute("passwordResetError", "Failed to reset password. Please check your details and try again.");
//        }
//        return "PasswordReset";
//    }
//}



//////////////////passwordReset impl


//package com.xworkz.issuemanagement.model.service;
//
//import com.xworkz.issuemanagement.model.repository.PasswordResetRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PasswordResetServiceImpl implements PasswordResetService {
//
//    @Autowired
//    private PasswordResetRepo passwordResetRepo;

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Override
//    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
//        if (!passwordResetRepo.emailExists(email)) {
//            return false;
//        }
//        if (!passwordResetRepo.verifyOldPassword(email, oldPassword)) {
//            return false;
//        }
//        if (!newPassword.equals(confirmPassword)) {
//            return false;
//        }
//        passwordResetRepo.updatePassword(email, newPassword);
//        sendPasswordEmail(email, "Password Reset Successful", "Your password has been successfully reset.");
//        return true;
//    }
//
//    @Override
//    public void sendPasswordEmail(String toEmail, String subject, String body) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(toEmail);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(body);
//        simpleMailMessage.setFrom("kmsrcb@gmail.com");
//        javaMailSender.send(simpleMailMessage);
//    }
//}
