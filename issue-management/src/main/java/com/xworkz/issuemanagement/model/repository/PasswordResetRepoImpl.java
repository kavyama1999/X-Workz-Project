package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@Repository
public class PasswordResetRepoImpl implements PasswordResetRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean emailExists(String email) {
        //check if email exists in database or not
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT COUNT(e) FROM SignUpDTO e WHERE e.email=:email";
        Query query1 = entityManager.createQuery(query);
        query1.setParameter("email", email);
        Long count = (Long) query1.getSingleResult();
        System.out.println(count);

        //return false;

        //return false;
        return  count > 0;
    }

    @Override
    public boolean verifyOldPassword(String email, String oldPassword) {

        //to verify  the old password

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT COUNT(e) FROM SignUpDTO e  WHERE email =:email AND e.password=:password";

        Query query1 = entityManager.createQuery(query);
        query1.setParameter("email", email);
        query1.setParameter("password", oldPassword);
        Long count= (Long) query1.getSingleResult();
        System.out.println(count);

        //return false;
        return count > 0;
    }

    @Override
   // @Transactional
    public void updatePassword(String email, String newPassword) {
        // to update the reset password to password in database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction= entityManager.getTransaction();


      try {

          entityTransaction.begin();
          //update table name set password=? where email=?;

          String query = "UPDATE SignUpDTO e SET e.password=:password WHERE e.email=:email ";

          Query query1 = entityManager.createQuery(query);
          query1.setParameter("password", newPassword);
          query1.setParameter("email", email);

          int executeData = query1.executeUpdate();
          System.out.println(executeData);
          entityTransaction.commit();

      }
      catch (Exception e)
      {
          e.printStackTrace();
          entityTransaction.rollback();
      }

      finally {

          entityManager.close();
      }

    }
}

