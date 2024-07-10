package com.xworkz.issuemanagement.model.repository;


import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@Repository
public class ViewUserRepoImpl implements ViewUserRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public SignUpDTO findByEmail(String email) {


        System.out.println(" findByEmail method running  in ViewUserRepoImpl ....");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT e FROM SignUpDTO e WHERE e.email=:email";
        Query query1 = entityManager.createQuery(query);
        query1.setParameter("email",email);

       SignUpDTO signUpDTO= (SignUpDTO) query1.getSingleResult();
        System.out.println(signUpDTO);

        return  signUpDTO;
//        return null;
    }
}

