package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class AdminRepoImpl implements AdminRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public AdminDTO findByEmailAndPassword(String email, String password) {


        System.out.println("findByEmailAndPassword  method in AdminRepo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT a FROM AdminDTO a where a.email=:emailId AND a.password=:adminPassword";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("emailId", email);
            query1.setParameter("adminPassword", password);

            AdminDTO data = (AdminDTO) query1.getSingleResult();
            System.out.println("Data: " + data);
            entityTransaction.commit();
            return data;
        } catch (NoResultException e) {
            System.out.println("No result found");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Admin connection closed");
        }

        return null;
    }

    @Override
    public List<SignUpDTO> findById(SignUpDTO signUpDTO) {

        System.out.println("findById method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT d FROM  SignUpDTO d";
            Query query1 = entityManager.createQuery(query);
            List<SignUpDTO> data = query1.getResultList();
            System.out.println("Data:" + data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return Collections.emptyList();
    }


    //admin can view raise complaint details
    @Override
    public List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO) {

        System.out.println("findById method in AdminRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT c FROM RaiseComplaintDTO c";
            Query query1 = entityManager.createQuery(query);
            List<RaiseComplaintDTO> data = query1.getResultList();
            System.out.println("RaiseComplaintData:" + data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("connection closed");
        }

        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO) {
        System.out.println("searchByComplaintType method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT c FROM RaiseComplaintDTO c WHERE c.complaintType=:ComplaintType";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("ComplaintType", raiseComplaintDTO.getComplaintType());
            List<RaiseComplaintDTO> raiseData = query1.getResultList();
            System.out.println("ComplaintType:" + raiseData);
            entityTransaction.commit();

            return raiseData;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            System.out.println("Connection closed");
            entityManager.close();
        }
        return Collections.emptyList();
    }


    //City
    @Override
    public List<RaiseComplaintDTO> searchComplaintByCity(RaiseComplaintDTO raiseComplaintDTO)
    {

        System.out.println("searchByComplaintType method running in AdminRepoImpl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            String query = "SELECT ct FROM RaiseComplaintDTO ct WHERE ct.city=:City";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("City", raiseComplaintDTO.getCity());
            List<RaiseComplaintDTO> cityType = query1.getResultList();
            System.out.println("CityType:" + cityType);
            entityTransaction.commit();

            return cityType;

        } catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        return Collections.emptyList();
    }
}

