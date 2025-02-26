package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
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

        employeeDTO.setStatus("Active"); //when i register in that time only  i gave to set status

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


    //fetch employee name  with department name from employeeTable
    @Override
    public List<EmployeeDTO> fetchEmployeeNamesByDepartment(String departmentName) {
        log.info("fetchEmployeeName method running in EmployeeRepoImpl...");

        EntityManager entityManager = entityManagerFactory.createEntityManager();


//      String query = "SELECT e.employeeName FROM EmployeeDTO e WHERE e.departmentName = :departmentName";
        //   String query = "SELECT e FROM EmployeeDTO e WHERE e.departmentName = :departmentName";
        //String jpql = "SELECT e FROM EmployeeDTO e WHERE e.status = 'active'";
        String query = "SELECT e FROM EmployeeDTO e WHERE e.departmentName = :departmentName AND e.status = 'active'";


        //  Query query1 = entityManager.createQuery("SELECT e FROM EmployeeDTO e where e.departmentName=:regDepartmentName AND e.status = 'ACTIVE'");

        Query query1 = entityManager.createQuery(query);
        query1.setParameter("departmentName", departmentName);

        List<EmployeeDTO> fetchEmployeeNames = query1.getResultList();
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

    //update

    @Override
    public boolean updateEmployeeForComplaint(int employeeId, int complaintId) {

        log.info("updateStatusAndEmployeeId method running in EmployeeRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String query = "UPDATE RaiseComplaintDTO c SET c.employeeDTO.employeeId = :employeeId  WHERE c.complaintId = :complaintId";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("employeeId", employeeId);
            query1.setParameter("complaintId", complaintId);

            int data = query1.executeUpdate();
            log.info("data :{}", data);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return true;

    }


    //***********************************************************
    //update delete status (iActive) in employee table and in raise complaint table employee_id fk  becomes null

    @Override
    public boolean updateEmployeeStatusToInActive(int employeeId, int complaintId) {

        log.info("updateEmployeeStatusToInactive method  running in EmployeeRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String jpqlUpdate = "UPDATE EmployeeDTO e SET e.status = 'inActive' WHERE e.employeeId = :employeeId";
            Query updateResult = entityManager.createQuery(jpqlUpdate);
            updateResult.setParameter("employeeId", employeeId);
            int updatedData = updateResult.executeUpdate();
            log.info("Updated Data: {}", updatedData);
            // entityTransaction.commit();

            // 2. Set employeeDTO(employeeID) to null in RaiseComplaintDTO
            String jpqlUpdateComplaint = "UPDATE RaiseComplaintDTO r SET r.employeeDTO = null WHERE r.complaintId= :complaintId";
            Query updateComplaint = entityManager.createQuery(jpqlUpdateComplaint);
            updateComplaint.setParameter("complaintId", complaintId);
            int updatedComplaints = updateComplaint.executeUpdate();
            log.info("RaiseComplaint employee reference updated to null: {}", updatedComplaints);

            // Commit the transaction after both operations
            entityTransaction.commit();

            return true;
        } catch (PersistenceException persistenceException) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback(); // Rollback in case of failure
            }
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }

        return false;
    }


    @Override
    public List<RaiseComplaintDTO> getParticularDepartments(String emailId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Corrected JPQL query to use the proper condition
//        String jpql = "SELECT r FROM RaiseComplaintDTO r WHERE r.emailId = :emailId";


//        String jpql = "SELECT r FROM RaiseComplaintDTO r WHERE r.employeeDTO.employeeId = :employeeId11";

        String jpql = "SELECT r FROM RaiseComplaintDTO r WHERE r.employeeDTO.emailId = :emailId";


        Query query = entityManager.createQuery(jpql);
        query.setParameter("emailId", emailId);

        List<RaiseComplaintDTO> allocatedComplaints = query.getResultList();

        return allocatedComplaints;
    }

    @Override
    public String updateStatusRaiseComplaintAndNotifyUser(int complaintId, String complaintStatus) {
//        log.info("updateStatusInComplaintRaiseTable method running in EmployeeRepoImpl");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        try {
//            entityTransaction.begin();
//            String updateQuery = "UPDATE RaiseComplaintDTO r set r.complaintStatus=:complaintStatus WHERE r.complaintId=:complaintId";
//            Query query = entityManager.createQuery(updateQuery);
//            query.setParameter("complaintStatus", complaintStatus);
//            query.setParameter("complaintId", complaintId);
//
//            int data = query.executeUpdate();
//            log.info("data : {}", data);
//            entityTransaction.commit();
//        } catch (Exception e) {
//
//
//            e.printStackTrace();
//            log.error("somthing went wrong");
//        } finally {
//            entityManager.close();
//        }


        log.info("updateStatusAndNotifyUser method running in EmployeeRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            // Update the status in the table
            String updateQuery = "UPDATE RaiseComplaintDTO r SET r.complaintStatus = :complaintStatus WHERE r.complaintId = :complaintId";
            Query query = entityManager.createQuery(updateQuery);
            query.setParameter("complaintStatus", complaintStatus);
            query.setParameter("complaintId", complaintId);
            int data = query.executeUpdate();
            log.info("Complaint status updated: {}", data);

            // Fetch the user's email if the status is "Completed"
            if ("Completed".equalsIgnoreCase(complaintStatus)) {
                String selectQuery = "SELECT r.signUpDTO.email FROM RaiseComplaintDTO r WHERE r.complaintId = :complaintId";
                String email = (String) entityManager.createQuery(selectQuery)
                        .setParameter("complaintId", complaintId)
                        .getSingleResult();
                log.info("Email of the user: {}", email);
                // Pass the email to the service for sending notifications
                entityTransaction.commit();
                return email;
            }

            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Something went wrong in updateStatusAndNotifyUser");
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;

    }

    @Override
    public void updateUserFeedback(int complaintId, String feedbackText) {
        log.info("updateFeedback method running in EmployeeRepoImpl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String updateQuery = "UPDATE RaiseComplaintDTO r SET r.feedbackText = :feedback WHERE r.complaintId = :complaintId";
            Query query = entityManager.createQuery(updateQuery);
            query.setParameter("feedback", feedbackText);
            query.setParameter("complaintId", complaintId);
            int updatedRecords = query.executeUpdate();
            log.info("Feedback updated successfully for complaintId: {}", updatedRecords);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error updating feedback: ", e);
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}


//when i select  employee name that id should go to save in raise complaint table

//1st ne
//@Override
//public boolean updateEmployeeStatusToInActive(String employeeName) {
//
//    log.info("updateEmployeeStatusToInactive method  running in EmployeeRepoImpl");
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    EntityTransaction entityTransaction = entityManager.getTransaction();
//
//    try {
//        entityTransaction.begin();
//        String jpqlUpdate = "UPDATE EmployeeDTO e SET e.status = 'inActive' WHERE e.employeeName = :employeeName";
//        Query updateResult = entityManager.createQuery(jpqlUpdate);
//        updateResult.setParameter("employeeName", employeeName);
//        int updatedData = updateResult.executeUpdate();
//        log.info("Updated Data: {}", updatedData);
//        entityTransaction.commit();
//
//        // Set employee reference in RaiseComplaintDTO to null
//        String jpqlUpdateComplaint = "UPDATE RaiseComplaintDTO r SET r.employeeDTO = null WHERE r.employee.employeeName = :employeeName";
//        entityManager.createQuery(jpqlUpdateComplaint)
//                .setParameter("employeeName", employeeName)
//                .executeUpdate();
//        return true;
//    } catch (PersistenceException persistenceException) {
//        persistenceException.printStackTrace();
//    } finally {
//        entityManager.close();
//        log.info("Connection closed");
//    }
////        return updatedData > 0;
//
//    return false;
//
//}
//
//
//
