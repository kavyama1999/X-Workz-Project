package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
public class SignUpRepoImpl  implements  SignUpRepo{


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SignUpRepoImpl() {
        System.out.println(" No param Constructor created for SignInRepoImpl ");
    }


    @Override
    public boolean userDataSave(SignUpDTO signUpDTO) {


            System.out.println("userDataSave method running in SignInRepoImpl");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try {
                entityTransaction.begin();
               // entityManager.persist(signUpDTO);
                entityManager.merge(signUpDTO);

                entityTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                entityTransaction.rollback();
            } finally {
                entityManager.close();
                System.out.println("Connection closed");
            }
            return true;
        }



}

