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
public class ForgotPasswordImpl implements ForgotPasswordRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public SignUpDTO findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT p FROM SignUpDTO p WHERE p.email = :email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            return (SignUpDTO) query1.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    //@Transactional
    @Override
    public void updatePassword(String email, String newPassword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String query = "UPDATE SignUpDTO s SET s.password = :newPassword WHERE s.email = :email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("newPassword", newPassword);
            query1.setParameter("email", email);
            query1.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
}
