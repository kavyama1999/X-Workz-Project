package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.RegisterDepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class AjaxRepoImpl implements AjaxRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public AjaxRepoImpl() {
        System.out.println("AjaxRepoImpl created");
    }

    @Override
    public SignUpDTO existsByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query2 = entityManager.createQuery("select sys from SignUpDTO sys where sys.email=:email ");
            query2.setParameter("email", email);
            System.out.println("Running existsByEmail method in SignUpRepoImpl");
            return (SignUpDTO) query2.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }


    @Override
    public SignUpDTO existsByContactNumber(Long contactNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select sys from SignUpDTO sys where sys.contactNumber=:contactNumber ");
            query.setParameter("contactNumber", contactNumber);
            System.out.println("Running existsByContactNumber method in SignUpRepoImpl");
            return (SignUpDTO) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;

    }


    //****************************************************************

    //for subAdmin registration page
    @Override
    public RegisterDepartmentAdminDTO existsByEmailId(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query2 = entityManager.createQuery("select sys from RegisterDepartmentAdminDTO sys where sys.email=:email ");
            query2.setParameter("email", email);
            System.out.println("Running existsByEmailId method in AjaxRepoImpl");
            return (RegisterDepartmentAdminDTO) query2.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;

    }




    @Override
    public RegisterDepartmentAdminDTO checkExistsByContactNumber(Long contactNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select sys from RegisterDepartmentAdminDTO sys where sys.contactNumber=:contactNumber ");
            query.setParameter("contactNumber", contactNumber);
            System.out.println("Running existsByContactNumber method in AjaxRepoImpl..");
            return (RegisterDepartmentAdminDTO) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }
}
