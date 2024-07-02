package com.xworkz.issuesmanagement.model.repository;

import com.xworkz.issuesmanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class SignUpRepoImpl implements SignUpRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SignUpRepoImpl() {
        System.out.println(" No param Constructor created for SignInRepoImpl ");
    }


    @Override
    public boolean userDataSave(SignUpDTO signUpDTO) {

        System.out.println("userDataSave method running in SignInRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(signUpDTO);
            //entityManager.merge(signUpDTO);

            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return true;
    }

    //password generate automatically
    @Override
    public SignUpDTO findByEmailAndPassword(String email, String password) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT s FROM SignUpDTO s where s.email=:email AND s.password=:password";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            query1.setParameter("password", password);
            SignUpDTO signUpDTO = (SignUpDTO) query1.getSingleResult();
            System.out.println(signUpDTO);
            entityTransaction.commit();
            return signUpDTO;

        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }


    //pruthvi
    @Override
    public SignUpDTO findByEmail(String email) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select c from SignUpDTO c where email=:email");
            query.setParameter("email", email);

            List<SignUpDTO> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                return null;
            } else if (resultList.size() == 1) {
                return resultList.get(0);
            } else {
                throw new NonUniqueResultException("Multiple results found for email: " + email);
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }


    }

    @Override
    public boolean update(SignUpDTO signUpDto) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.merge(signUpDto);
            tx.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;


    }


    ///to avoid duplicate email

//    @Override
//    public SignUpDTO findByExistsEmail(String email) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        System.out.println("Repo method");
//        try {
//            Query query = entityManager.createQuery("select c from SignUpDTO c where c.email=:email ");
//            query.setParameter("email", email);
//            SignUpDTO signUpDTO = (SignUpDTO) query.getSingleResult();
//            return signUpDTO;
//        } catch (PersistenceException persistenceException) {
//            persistenceException.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//        return null;
//    }







    @Override
    public SignUpDTO findByExistsEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Repo method");
        try {
            Query query = entityManager.createQuery("select c from SignUpDTO c where c.email = :email");
            query.setParameter("email", email);
            SignUpDTO signUpDTO = (SignUpDTO) query.getSingleResult();
            return signUpDTO;
        } catch (NoResultException noResultException) {
            // Handle the case where no entity is found for the query
            System.out.println("No entity found for the given email: " + email);
            return null; // or throw a custom exception, or handle it as per your application logic
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

}
    ///to avoid duplicate email




















