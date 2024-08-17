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
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }

        return false;
    }


    //subAdmin login id email exists in database
//we also match here department name
    @Override
    public RegisterDepartmentAdminDTO findEmailAndPassword(String email, String password, String departmentName) {
        log.info("findEmailAndPassword method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT m FROM RegisterDepartmentAdminDTO m WHERE m.email=:email AND m.password=:password AND m.departmentName=:departmentName";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            query1.setParameter("password", password);
            query1.setParameter("departmentName", departmentName);
            RegisterDepartmentAdminDTO registerDepartmentAdminDTO = (RegisterDepartmentAdminDTO) query1.getSingleResult();
            log.info("registerDepartmentAdminDTO : {}", registerDepartmentAdminDTO);
            entityTransaction.commit();
            return registerDepartmentAdminDTO;

        } catch (NoResultException e) {
            e.printStackTrace();
            log.info("No entity found for query");
            return null;  // Or handle the case where no result is found
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return null;
        } finally {
            log.info("Connection closed");

            entityManager.close();
        }


    }


    //to check and match email and password

//    @Override
//    public RegisterDepartmentAdminDTO findByEmail(String email) {
//
//        System.out.println("findByEmail method running in AdminRepoImpl..");
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//
//        try {
//            System.out.println(" Existing Email : " + email);
//            String query = "SELECT  e From  RegisterDepartmentAdminDTO e WHERE e.email=:email";
//            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("email", email);
//
//            RegisterDepartmentAdminDTO data = (RegisterDepartmentAdminDTO) query1.getSingleResult();
//            System.out.println("email :" + data);
//            entityTransaction.commit();
//            return data;
//
//        } catch (PersistenceException e) {
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//            log.info("Connection closed");
//        }
//        return null;
//    }



    //to check whether email  exists or not in  database or not

    @Override
    public RegisterDepartmentAdminDTO findByEmail(String email) {
        System.out.println("findByEmail method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            System.out.println(" Existing Email : " + email);
            String query = "SELECT e FROM RegisterDepartmentAdminDTO e WHERE e.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            RegisterDepartmentAdminDTO data = (RegisterDepartmentAdminDTO) query1.getSingleResult();
            System.out.println("email :" + data);
            entityTransaction.commit();
            return data;

        } catch (NoResultException e) {
            System.out.println("No entity found for query");
            entityTransaction.rollback(); // Rollback the transaction in case of no result
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityTransaction.rollback(); // Rollback the transaction in case of other persistence exceptions
        } finally {
            log.info("Connection closed");

            entityManager.close();
        }
        return null;
    }

    @Override
    public RegisterDepartmentAdminDTO resetPasswordEmail(String email) {

        log.info("resetPasswordEmail method running in AdminRepoImpl..{}", email);
        System.out.println("resetPasswordEmail : " + email);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT f FROM RegisterDepartmentAdminDTO f WHERE f.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            RegisterDepartmentAdminDTO findEmail = (RegisterDepartmentAdminDTO) query1.getSingleResult();

            log.info("FindEmail : {}", findEmail);

            return findEmail;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            log.info("Connection closed");

            entityManager.close();
        }
        return null;
    }

    //**************************************************

    //to update forgot  password in database
    @Override
    public void updatePassword(String email, String password) {

        log.info("updatePassword method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "UPDATE RegisterDepartmentAdminDTO u SET u.password=:newPassword WHERE u.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("newPassword", password);
            query1.setParameter("email", email);

            int update = query1.executeUpdate();
            log.info("forgotPasswordUpdate :{}", update);
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("Connection closed");

            entityManager.close();
        }

    }

    //***********************************************************

    @Override
    public boolean update(RegisterDepartmentAdminDTO registerDepartmentAdminDTO) {
        log.info("update method running in AdminRepoImpl..");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(registerDepartmentAdminDTO);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityManager.close();
        } finally {
            entityManager.close();
        }


        return true;
    }

    //****************************************************
    // Department Admin change password
    @Override
    public RegisterDepartmentAdminDTO emailExists(String email) {

        //check if email exists in database or not

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT d FROM RegisterDepartmentAdminDTO d WHERE d.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            RegisterDepartmentAdminDTO emailExistsOrNot = (RegisterDepartmentAdminDTO) query1.getSingleResult();

            System.out.println("EmailExists : " + emailExistsOrNot);
            return emailExistsOrNot;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }

        return null;
    }


    @Override
    public RegisterDepartmentAdminDTO verifyOldPassword(String email, String oldPassword) {

        log.info("verifyOldPassword method running in AdminRepoImpl");
        //to verify  the old password
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT s FROM RegisterDepartmentAdminDTO s where s.email=:email AND s.password=:oldPassword";
            Query query1 = entityManager.createQuery(query);

            query1.setParameter("email", email);
            query1.setParameter("oldPassword", oldPassword);

            RegisterDepartmentAdminDTO verifyOldPassword = (RegisterDepartmentAdminDTO) query1.getSingleResult();

            System.out.println("verifyOldPassword : " + verifyOldPassword);
            return verifyOldPassword;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }

        return null;
    }


    @Override
    public boolean departmentAdminUpdatePassword(String email, String newPassword) {
        log.info("subAdminUpdatePassword method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        try {
            entityTransaction.begin();
            String query = "UPDATE RegisterDepartmentAdminDTO e SET e.password=:password WHERE e.email=:email ";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("password", newPassword);
            query1.setParameter("email", email);


            int executeData = query1.executeUpdate();
            System.out.println(executeData);
            entityTransaction.commit();

            return true;


        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            log.info("CConnection closed");
        }


    }


    //*************************************
    //to save department id in Department admin table
    @Override
    public DepartmentDTO findDepartmentByName(String departmentName) {

        System.out.println("findDepartmentByName method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT d FROM DepartmentDTO d WHERE d.departmentName=:departmentName";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("departmentName", departmentName);
            DepartmentDTO departmentId = (DepartmentDTO) query1.getSingleResult();
            System.out.println("DepartmentId-----------> ; " + departmentId);
            return departmentId;
        } catch (NoResultException e) {
            System.out.println("No entity found for query-------->");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }


    //******************************************************************************************

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        System.out.println("Running getAllDepartments method in Department admin repo implementation...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT d FROM DepartmentDTO d";
            Query query1 = entityManager.createQuery(query);
            List<DepartmentDTO> resultList = query1.getResultList();
            System.out.println("ResultList size: " + resultList.size());
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }


    //**************************************************************
    //department admin can view only particular
    @Override
    public List<RaiseComplaintDTO> getParticularDepartments(String complaintType) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();



        //String jpql = "SELECT r FROM RaiseComplaintDTO r WHERE r.complaintType=:complaintType";
         String jpql = "SELECT r FROM RaiseComplaintDTO r WHERE r.complaintType = :complaintType";

        Query query1 = entityManager.createQuery(jpql);
        query1.setParameter("complaintType", complaintType);
        List<RaiseComplaintDTO> getAllDetails = query1.getResultList();

        return getAllDetails;
        // String jpql = "SELECT r FROM RaiseComplaintDTO r WHERE r.departmentDTO.departmentName = :departmentName";

    }

    //    return Collections.emptyList();
    }


