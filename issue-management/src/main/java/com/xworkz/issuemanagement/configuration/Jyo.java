//@Override
//public boolean deleteAllocatedEmployee(String employeeName) {
//    boolean updateSuccess = employeeRepo.updateEmployeeStatusToInActive(employeeName);
//
//    if (updateSuccess) {
//        boolean setEmployeeIdNullSuccess = setEmployeeIdToNullInComplaints(employeeName);
//        return setEmployeeIdNullSuccess;
//    }
//
//    return false;
//}
//
//// Method to set employee_id to null in complaint_raise table
//private boolean setEmployeeIdToNullInComplaints(String employeeName) {
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    EntityTransaction entityTransaction = entityManager.getTransaction();
//
//    try {
//        entityTransaction.begin();
//        // Assuming the complaint_raise table is mapped to RaiseComplaintDTO and has a reference to EmployeeDTO
//        String jpqlUpdate = "UPDATE RaiseComplaintDTO c SET c.employeeDTO = null WHERE c.employeeDTO.employeeName = :employeeName";
//        Query updateQuery = entityManager.createQuery(jpqlUpdate);
//        updateQuery.setParameter("employeeName", employeeName);
//        int updatedCount = updateQuery.executeUpdate();
//
//        entityTransaction.commit();
//        return updatedCount > 0;
//    } catch (PersistenceException e) {
//        e.printStackTrace();
//        if (entityTransaction.isActive()) {
//            entityTransaction.rollback();
//        }
//        return false;
//    } finally {
//        entityManager.close();
//    }
//}
