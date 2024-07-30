package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.*;
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


    //admin can view all userraise complaint details
    @Override
    public List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO) {

        System.out.println("findById method in AdminRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            //String query = "SELECT c FROM RaiseComplaintDTO c";
            String query = "SELECT c FROM RaiseComplaintDTO c ORDER BY c.complaintId DESC";

//   TypedQuery<ComplaintsDTO> query = entityManager.createQuery
//   ("SELECT s FROM ComplaintsDTO s ORDER BY s.createdAt DESC", ComplaintsDTO.class);

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

    //admin can view TypeAndCity
    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchByComplaintType method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT c FROM RaiseComplaintDTO c WHERE c.city=:City   And c.complaintType = :ComplaintType  ";
//            String query = "SELECT r FROM RaiseComplaintDto r where r.city=:city OR r.complaintType=:complaintTypes";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("ComplaintType", complaintType);
            query1.setParameter("City", city);
            List<RaiseComplaintDTO> list = query1.getResultList();
            System.out.println("ComplaintTypeAndCityData: " + list);
            entityTransaction.commit();

            return list;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            System.out.println("Connection closed");
            entityManager.close();
        }
        return Collections.emptyList();
    }


    //type OR city
    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminRepoImpl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT ct FROM RaiseComplaintDTO ct WHERE  ct.city =:City OR ct.complaintType =:ComplaintType ";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("ComplaintType", complaintType);
            query1.setParameter("City", city);
            List<RaiseComplaintDTO> list = query1.getResultList();
            System.out.println("ListOfTypeOrCity: " + list);
            entityTransaction.commit();

//String query = "SELECT r FROM RaiseComplaintDto r where r.city=:city OR r.complaintType=:complaintTypes";

            return list;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return Collections.emptyList();
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        System.out.println("saveDepartment method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(departmentDTO);
            entityTransaction.commit();

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return departmentDTO;
    }


    //fetch all department
    @Override
    public List<DepartmentDTO> findAll(String departmentName) {
        System.out.println("findAll method running AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        try {
            String query = "SELECT d FROM DepartmentDTO d";
            //String query = "SELECT d FROM ComplaintDepartmentDTO d WHERE d.departmentType = :departmentType";

            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("departmentType", departmentType);
            List<DepartmentDTO> data = query1.getResultList();
            System.out.println("DepartmentName : " + data);

            return data;

        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return null;
    }


    //update and status and departmentId


    @Override
    public void updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {
        System.out.println("updateStatusAndDepartmentId method running in RaiseComplaintRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String updateQuery = "UPDATE RaiseComplaintDTO r SET r.departmentDTO.id = :departmentId, r.status = :status WHERE r.complaintId = :complaintId";
            Query query = entityManager.createQuery(updateQuery);
            query.setParameter("departmentId", departmentId);
            query.setParameter("status", status);
            query.setParameter("complaintId", complaintId);

            int data = query.executeUpdate();
            System.out.println("data :" + data);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    //save department admin data
    @Override
    public boolean saveDepartmentAdminData(RegisterDepartmentAdminDTO registerDepartmentAdminDTO) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        try {
            entityTransaction.begin();
            entityManager.persist(registerDepartmentAdminDTO);
            entityTransaction.commit();

            return true;
        } catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        } finally
        {
            entityManager.close();
            log.info("Connection closed");
        }

        return false;
    }
}

