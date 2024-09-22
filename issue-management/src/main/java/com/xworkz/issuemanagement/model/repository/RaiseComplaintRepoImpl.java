package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class RaiseComplaintRepoImpl implements RaiseComplaintRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;


    //save
    @Override
    public boolean saveRaiseComplaintData(RaiseComplaintDTO raiseComplaintDTO) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            //entityManager.persist(raiseComplaintDTO);
            entityManager.merge(raiseComplaintDTO);
            entityTransaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            log.error("not saved");
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
        return true;
    }

    //to set foreign key
    @Override
    public Optional<RaiseComplaintDTO> findByUserId(int id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
                    "SELECT i FROM RaiseComplaintDto i WHERE i.cmplt_id = :id", RaiseComplaintDTO.class);
            query.setParameter("id", id);

            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }

    }

//    @Override
//    public List<RaiseComplaintDTO> findByRaiseComplaint(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
//                    "SELECT r FROM RaiseComplaintDTO r WHERE r.signUpDTO.id = :userId", RaiseComplaintDTO.class);
//            query.setParameter("userId", id);
//            return query.getResultList();
//        } finally {
//            entityManager.close();
//        }
//    }


    //to view RaiseComplaint data
    @Override
    public List<RaiseComplaintDTO> findByRaiseComplaint(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
                    "SELECT r FROM RaiseComplaintDTO r WHERE r.signUpDTO.id = :userId ORDER BY r.complaintId DESC ", RaiseComplaintDTO.class);
           // "SELECT r FROM RaiseComplaintDTO r WHERE r.signUpDTO.id = :userId ", RaiseComplaintDTO.class);

            query.setParameter("userId", id);
            List<RaiseComplaintDTO> results = query.getResultList();
            log.info("Found {} complaints for user ID {}", results.size(), id);
            return results;
        } finally {
            entityManager.close();
        }
    }



    //edit

    @Override
    public Optional<RaiseComplaintDTO> findByComplaintId(int complaintId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
                    "SELECT r FROM RaiseComplaintDTO r WHERE r.complaintId = :complaintId", RaiseComplaintDTO.class);
            query.setParameter("complaintId", complaintId);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }

    //update
    @Override
    public RaiseComplaintDTO updateRaiseComplaintUserDetails(RaiseComplaintDTO raiseComplaintDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try
        {
            entityTransaction.begin();
            entityManager.merge(raiseComplaintDTO);
            entityTransaction.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityTransaction.rollback();
        }
        finally {
            entityManager.close();
            log.info("updateRaiseComplaintUserDetails connection closed");
        }

        return raiseComplaintDTO;
    }




}




