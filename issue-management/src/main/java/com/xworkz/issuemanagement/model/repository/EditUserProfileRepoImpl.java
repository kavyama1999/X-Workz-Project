package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EditProfileImageDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EditUserProfileRepoImpl implements EditUserProfileRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EditUserProfileRepoImpl() {
        System.out.println("No parameters in EditUserDetailsRepoImpl..");
    }

    @Override
    public SignUpDTO findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT e From SignUpDTO e WHERE e.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            // Use getResultList() instead of getSingleResult()
            List<SignUpDTO> results = query1.getResultList();

            if (!results.isEmpty()) {
                return results.get(0);// Return the first result if found
            }

        } catch (NoResultException e) {
            // Handle case where no results are found
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return null;// Return null if no results found
    }

    @Override
    public void updateUserDetails(SignUpDTO signUpDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(signUpDTO); // Ensure signUpDTO is managed or retrieved first
            //entityManager.flush(); // Flush changes to the database
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }


}
