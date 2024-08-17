<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>





<style>
        .extra-margin-top {
            margin-top: 200px; /* Adjust this value to get the desired margin */
        }



     /*  body {
                    background-color: ; /* Change this to the desired background color  #e6f7ff*/
                }*/
    </style>



<style>
        .dropdown-toggle-custom {
            color: #fff;
            background-color: transparent;
            border: 1px solid #fff;
            padding: 5px 10px;
            border-radius: 5px;
        }
        .dropdown-toggle-custom:hover {
            background-color: #495057; /* Darker grey for hover */
        }
    </style>


</head>

<body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://www.x-workz.in/Logo.png" alt="xworkz" width="140" height="70" />
                </a>
                <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
             <a class="navbar-brand text-white" href="Admin"><b>Admin</b></a>

            </div>


 <div class="dropdown">
                    <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        Admin
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                    <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>

                   <li><a class="dropdown-item" href="View-raise-complaint"><strong>ViewRaiseComplaintDetails</strong></a></li>

                   <li><a class="dropdown-item" href="add-complaints"><strong>AddComplaints</strong></a></li>

                 <!--  <li><a class="dropdown-item" href="addDepartmentPage"><strong>AddDepartmentAdmin</strong></a></li>--!>

                   <li><a class="dropdown-item" href="departmentAdmin"><strong>AddDepartmentAdmin</strong></a></li>




                  <li><a class="dropdown-item" href="departmentAdminLogIn"><strong>SubAdminLoginPage</strong></a></li>


                    </ul>
                </div>



        </div>
    </nav>












  <form action="/edit-profile" method="post">

      <center>
          <strong style="color:blue">
              <h3 class="mt-5   extra-margin-top">${AdminProfilePageMessage}</h3>
          </strong>
      </center>

  </form>







</body>
</html>

















