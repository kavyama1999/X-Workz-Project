package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Optional;

@Repository
@Slf4j
public class ImageUploadRepoImpl implements ImageUploadRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveImage(EditProfileImageDTO editProfileImageDTO) {
        log.info("saveImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(editProfileImageDTO);
            entityTransaction.commit();
            return true; // Return true on successful commit
        } catch (Exception e) {
            log.error("Error saving image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return false; // Return false on failure
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
    }


    //foreign key set
    @Override
    public Optional<EditProfileImageDTO> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT p FROM EditProfileImageDTO p WHERE p.user = :id"; //imageUserId
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
            entityTransaction.commit();
            return query1.getResultList().stream().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return Optional.empty();
        } finally {
            entityManager.close(); // Ensure the EntityManager is closed after the operation
        }
    }

    @Override
    public void imageUpdateDetails(EditProfileImageDTO editProfileImageDTO) {

        log.info("updateImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(editProfileImageDTO);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error updating image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }


    }

    @Override
    public void SetAllImagesInactiveForUser(int id) {
        {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try {
                entityTransaction.begin();
//                    String query = "UPDATE EditProfileImageDTO e SET e.status = 'Inactive' WHERE e.user.id = :userId";

                String query = "UPDATE EditProfileImageDTO  SET status = 'Inactive' WHERE user.id = :userId";

                Query updateQuery = entityManager.createQuery(query);
                updateQuery.setParameter("userId", id);
                int updatedCount = updateQuery.executeUpdate();

                log.info("Number of images set inactive: {}", updatedCount);
                entityTransaction.commit();
            } catch (Exception e) {
                log.error("Error setting images inactive for user with ID {}: {}", id, e.getMessage());
                if (entityTransaction != null && entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
            } finally {
                entityManager.close();
                log.info("Connection closed for SetAllImagesInactiveForUser..");
            }
        }
    }
}