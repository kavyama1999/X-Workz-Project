//@Repository
//public class EditUserProfileRepoImpl implements EditUserProfileRepo {
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    public EditUserProfileRepoImpl() {
//        System.out.println("No parameters in EditUserProfileRepoImpl..");
//    }
//
//    @Override
//    public SignUpDTO findByEmail(String email) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        try {
//            String query = "SELECT e FROM SignUpDTO e WHERE e.email = :email";
//            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("email", email);
//
//            // Use getResultList() instead of getSingleResult()
//            List<SignUpDTO> results = query1.getResultList();
//            if (!results.isEmpty()) {
//                return results.get(0); // Return the first result if found
//            }
//        } catch (NoResultException e) {
//            // Handle case where no results are found
//            e.printStackTrace(); // Log or handle the exception
//        } finally {
//            entityManager.close();
//        }
//
//        return null; // Return null if no results found
//    }
//
//    @Override
//    public void updateUserDetails(SignUpDTO signUpDTO) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        try {
//            entityTransaction.begin();
//            entityManager.merge(signUpDTO);
//            entityTransaction.commit();
//        } catch (Exception e) {
//            if (entityTransaction.isActive()) {
//                entityTransaction.rollback();
//            }
//            e.printStackTrace(); // Log or handle the exception
//        } finally {
//            entityManager.close();
//        }
//    }
//}
