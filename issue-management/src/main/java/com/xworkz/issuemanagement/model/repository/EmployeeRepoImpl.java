package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

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
            entityManager.merge(employeeDTO); //persit
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


    //fetch employee name from employeeTable
    @Override
    public List<String> fetchEmployeeName() {
        log.info("fetchEmployeeName method running in EmployeeRepoImpl...");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //String query="SELECT employeeName from employeeDTO";
        String query = "SELECT e.employeeName FROM EmployeeDTO e";
        Query query1 = entityManager.createQuery(query);

        List<String> fetchEmployeeNames = query1.getResultList();
        log.info("ListOfEmployeeNames: {}", fetchEmployeeNames);

        return fetchEmployeeNames;
    }



    //***************************************************************
    //to check whether email exists or not in database
    @Override
    public EmployeeDTO findByEmail(String emailId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            //System.out.println("Existing email:" +emailId);
            log.info("Existing email : {}", emailId);
            entityTransaction.begin();
            String query = "SELECT e FROM EmployeeDTO e WHERE e.emailId =:emailId";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("emailId", emailId);
            EmployeeDTO employeeDTO = (EmployeeDTO) query1.getSingleResult();

            log.info("EmployeeDTO data :{}", employeeDTO);
            entityTransaction.commit();

            return employeeDTO;


        } catch (NoResultException exception) {
            log.warn("No entity found for email: {}", emailId); // Log a warning instead of printing the stack trace

        } catch (PersistenceException persistenceException) {
            log.error("PersistenceException occurred while finding employee by email: {}", emailId, persistenceException);
            entityTransaction.rollback(); // Rollback transaction in case of persistence exception

        } finally {
            log.info("findByEmail method closed");
            if (entityTransaction.isActive()) {
                entityTransaction.rollback(); // Ensure transaction is rolled back if still active
            }
            entityManager.close();
        }

        return null;
    }
}
