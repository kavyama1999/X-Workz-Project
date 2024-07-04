//package com.xworkz.issuemanagement.model.repository;
//
//import com.xworkz.issuemanagement.dto.SignUpDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Repository
//public class MailRepoImpl implements MailRepo {
//
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//
//    @Override
//    public SignUpDTO findByEmailAndPassword(String email, String password) {
//
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        try {
//            entityTransaction.begin();
//            String query = "SELECT s FROM SignUpDTO s where s.email=:email AND s.password=:password";
//            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("email", email);
//            query1.setParameter("password", password);
//            SignUpDTO signUpDTO = (SignUpDTO) query1.getSingleResult();
//            System.out.println(signUpDTO);
//            entityTransaction.commit();
//            return signUpDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            entityTransaction.rollback();
//        } finally {
//            entityManager.close();
//        }
//        return null;
//    }
//
//
//    //pruthvi
//    @Override
//    public SignUpDTO findByEmail(String email) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            Query query = entityManager.createQuery("select c from SignUpDTO c where email=:email");
//            query.setParameter("email", email);
//
//            List<SignUpDTO> resultList = query.getResultList();
//            if (resultList.isEmpty()) {
//                return null;
//            } else if (resultList.size() == 1) {
//                return resultList.get(0);
//            } else {
//                throw new NonUniqueResultException("Multiple results found for email: " + email);
//            }
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            entityManager.close();
//        }
//
//    }
//
//    @Override
//    public boolean update(SignUpDTO signUpDto) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction tx = entityManager.getTransaction();
//
//        try {
//            tx.begin();
//            entityManager.merge(signUpDto);
//            tx.commit();
//        } catch (PersistenceException persistenceException) {
//            persistenceException.printStackTrace();
//            tx.rollback();
//            return false;
//        } finally {
//            entityManager.close();
//        }
//        return true;
//    }
//}
//
///////////////////////



//@Service
//public class ResetPasswordServiceImpl implements ResetPasswordService {
//
//    @Autowired
//    private ResetPasswordRepo resetPasswordRepo;
////
////    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    private MailService mailService;
//
//    @Override
//    public boolean resetPassword(String email) {
//        System.out.println("resetPassword method running in ResetPasswordServiceImpl");
//
//        SignUpDTO user = resetPasswordRepo.findByEmail(email);
//        if (user != null) {
//            String newPassword = EmailPasswordGenerator.generatePassword();
//            resetPasswordRepo.updatePassword(email, newPassword);
//
//            // Reset failed attempts
//            mailService.resetFailedAttempts(email);
//
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(email);
//            message.setSubject("Password Reset");
//            message.setText("Your new password is: " + newPassword);
//            javaMailSender.send(message);
//
//            return true;
//        }
//        return false;
//    }
//}
//
