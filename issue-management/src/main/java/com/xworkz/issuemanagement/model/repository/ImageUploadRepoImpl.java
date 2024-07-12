package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
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
        } catch (Exception e)
        {
            log.error("Error saving image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive())
            {
                entityTransaction.rollback();
            }
            return false; // Return false on failure
        }
        finally
        {
            entityManager.close();
            log.info("Connection closed");
        }
    }

    @Override
    public Optional<EditProfileImageDTO> findByUserId(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction =  entityManager.getTransaction();

        try {
            String query = "SELECT p FROM EditProfileImageDTO p WHERE p.imageUserId = :id";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
          return query1.getResultList().stream().findFirst();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityTransaction.rollback();
            return Optional.empty();
        } finally
        {
            entityManager.close(); // Ensure the EntityManager is closed after the operation
        }
    }
}