
<!--it is used for expression language--!>


<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Form</title>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--!>

<!--for dropdown--!>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>



</head>
    <body>

    <nav class="navbar navbar-dark bg-dark">
    	<div class="container-fluid">
    		<div class="navbar-header">


    		<!-- Add your logo here -->

                        <a class="navbar-brand" href="#">
                            <img src="https://www.x-workz.in/Logo.png" alt="xworkz" width="140" height="70" />
                        </a>

            <!-- End of logo -->

            <a class="navbar-brand text-white" href="index.jsp"><b>Home</b></a>



</div>

<div class="dropdown">

 <div class=" dropdown-toggle"  id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

     <!--<img src="${pageContext.request.contextPath}/images/profileicon.jpg" alt="Profile" width="30" height="30" class="rounded-circle"> <!-- Add your profile icon path -->
     <img src="https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg" alt="Profile" width="40" height="40" class="rounded-circle"> <!-- Add your profile icon path -->

            </div>
  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

    <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>

    <!--<li><a class="dropdown-item" href="edit"><strong>Edit</strong></a></li>--!>

    <!--<td> <a href="country-edit?id=${country.id}">Edit</td>--!>

    <li><a class="dropdown-item" href="PasswordReset.jsp"><strong>Password Reset</strong></a></li>
    <li><a class="dropdown-item" href="view-profile" ><strong>View</strong></a></li>
  </ul>
</div>


   </div>
 </nav>


<form action="success" method="post">
    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
            <div class="card-body">
                <strong style="color:blue"><h3>${ProfilePageMessage}</h3></strong>
            </div>
        </div>
    </div>
</form>

  </body>
</html>