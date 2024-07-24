//
//
//<%@ page isELIgnored="false"%>
//<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
//
//
//<!DOCTYPE html>
//<html>
//<head>
//<meta charset="ISO-8859-1">
//<title>Student Form</title>
//<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
//
//<!---<script src="/winter_database/js/event.js"></script>--!>
//
// </head>
//<body>
//<nav class="navbar navbar-dark bg-dark">
//
//    <div class="container-fluid">
//        <div class="navbar-header">
//            <!-- Add your logo here -->
//            <a class="navbar-brand" href="#">
//                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
//            </a>
//            <!-- End of logo -->
//
//            <a class="navbar-brand text-white" href="index.jsp"><b>Home</b></a>
//           <!--<a href="collegeUniform.jsp" <span  class="navbar-brand mb-0 h1">CollegeUniformForm</span></a>--!>
//
//        </div>
//    </div>
//</nav>
//
//<div class="container   mt-5 w-100  mb-5 d-flex justify-content-center" ">
//
//    <div class="card p-4 ">
//        <div class="card-body">
//
//            <form action="CountrySearched" method="post">
//
//                <!--<h3><b>Event Registration Form</b></h3>--!>
//
//                <!--<div class="text-primary">${EventName}</div>---!>
//
//                <span style="color:red;">
//                    <c:forEach items="${errors}" var="objectError">
//${objectError.defaultMessage}<br>
//                    </c:forEach>
//                </span>
//
//
//
//
//
//
//
//
// <div class="row mb-3">
//                               <span id="countryNameError"></span>
//                               <label for="countryName" class="form-label"><b>CountryName:</b></label>
//                               <select class="form-select custom-select-width" id="countryName" onblur="countryNameValidation()" name="countryName">
//                                   <option value="" ${countryDTO.countryName == null ? 'selected' : ''}>Select</option>
//
//                   <option value="India" ${countryDTO.countryName == 'India' ? 'selected' : ''}> India</option>
//                   <option value="Germany" ${countryDTO.countryName == 'Germany' ? 'selected' : ''}>Germany</option>
//                   <option value="Italy" ${countryDTO.countryName == 'Italy' ? 'selected' : ''}> Italy</option>
//                   <option value="Japan" ${countryDTO.countryName == 'Japan' ? 'selected' : ''}>Japan</option>
//                   <option value="Mexico" ${countryDTO.countryName == 'Mexico' ? 'selected' : ''}> Mexico</option>
//                   <option value="Russia" ${countryDTO.countryName == 'Russia' ? 'selected' : ''}>Russia</option>
//                   <option value="Saudi Arabia" ${countryDTO.countryName == 'Saudi Arabia' ? 'selected' : ''}> Saudi Arabia</option>
//                   <option value="China" ${countryDTO.countryName == 'China' ? 'selected' : ''}> China</option>
//                   <option value="South Korea" ${countryDTO.countryName == 'Phone' ? 'selected' : ''}>South Korea</option>
//                   <option value="Turkey" ${countryDTO.countryName == 'Turkey' ? 'selected' : ''}> Turkey</option>
//                   <option value="United Kingdom" ${countryDTO.countryName == 'United Kingdom' ? 'selected' : ''}>United Kingdom</option>
//                   <option value="France" ${countryDTO.countryName == 'France' ? 'selected' : ''}>France</option>
//
//
//                               </select><br>
//                           </div>
//
//
//
//
//               <div>
//                  <input type="submit" id="submit" value="Submit" >
//               </div>
//
//            </form>
//
//        </div>
//    </div>
//</div>
//
//
//<!--for table to display--!>
//
//
//<div class="container   mt-5   mb-5 d-flex justify-content-center">
//
//    <div class="card p-4 ">
//        <div class="card-body">
//
//     <!-- <strong style="color:green;">Search result for,${EventName}</strong>--!>
//
//
//
//           <!--- <form action="CountrySearched" method="post">--!>
//
//                <!--<h3><b>Event Registration Form</b></h3>--!>
//
//                <div class="text-primary">${CountryName}</div>
//
//            <table class="table">
//          <thead>
//
//            <tr>
//
//              <th scope="col">ID</th>
//              <th scope="col">CountryName </th>
//              <th scope="col">Population </th>
//              <th scope="col">CapitalCity</th>
//              <th scope="col">NoOfStates</th>
//             <th scope="col">Edit</th>
//                          <th scope="col" >Delete</th>
//
//
//
//            </tr>
//
//
//          </thead>
//
//          <tbody>
//               <c:forEach items="${listOfCountries}" var="country">
//
//                <!--var can be anything---!>
//
//
//                      <tr>
//
//
//
//<td>${country.id}</td>
//<td>${country.countryName}</td>
//<td>${country.population}</td>
//<td>${country.capitalCity}</td>
//<td>${country.noOfStates}</td>
//<td> <a href="country-edit?id=${country.id}">Edit</td>
//<td> <a href="country-delete?id=${country.id}">Delete</td>
//
//
//
//                      </tr>
//
//
//<!--jstl tag to print all details--!>
//
//               </c:forEach>
//
//          </tbody>
//        </table>
//
//
// </div>
//    </div>
//</div>
//
//</body>
//</html>

//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import javax.persistence.PersistenceException;
//import javax.persistence.Query;
//import java.util.Collections;
//import java.util.List;*********************************************************************
//@Override
//public List<CountryDTO> countrySearch(CountrySearchDTO countrySearchDTO) {
//
//    System.out.println("CountrySearch method ruuning in CountryRepoImpl");
//
//    //EntityMangerFactory will created by spring
//
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    EntityTransaction entityTransaction = entityManager.getTransaction();
//    entityTransaction.begin();
//
//    try {
//        String query = "SELECT ct FROM CountryDTO ct where ct.countryName=:country";
//        Query query1 = entityManager.createQuery(query);
//        query1.setParameter("country", countrySearchDTO.getCountryName());
//        List<CountryDTO> countryDTOS = query1.getResultList();
//
//        entityTransaction.commit();
//        return countryDTOS;
//    } catch (PersistenceException persistenceException) {
//        persistenceException.printStackTrace();
//        entityTransaction.rollback();
//    } finally {
//        System.out.println("Connection closed");
//
//        entityManager.close();
//
//    }
//
//
//    return Collections.emptyList();
//}
