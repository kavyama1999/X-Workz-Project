package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Slf4j
public class EmployeeRepoImpl implements EmployeeRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveEmployeeData(EmployeeDTO employeeDTO) {

        log.info("saveEmployeeData method in EmployeeRepoImpl...");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(employeeDTO);
            entityTransaction.commit();
            return true;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            log.info("Connection closed");
            entityManager.close();
        }
        return false;
    }


    //***************************************************************
    //to check whether email exists or not in database
//    @Override
//    public EmployeeDTO findByEmail(String emailId) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        try {
//            //System.out.println("Existing email:" +emailId);
//            log.info("Existing email : {}", emailId);
//            entityTransaction.begin();
//            String query = "SELECT e FROM EmployeeDTO e WHERE e.emailId =:emailId";
//
//            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("emailId", emailId);
//            EmployeeDTO employeeDTO = (EmployeeDTO) query1.getSingleResult();
//
//            log.info("EmployeeDTO data :{}", employeeDTO);
//            entityTransaction.commit();
//
//            return employeeDTO;
//
//
//        }
//
//        catch (PersistenceException persistenceException)
//        {
//            persistenceException.printStackTrace();
//        }
//        finally {
//            log.info("findByEmail method closed");
//            entityManager.close();
//        }
//        return null;
//    }
}
