<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>
  <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>--!>



    <!-- <title>Bootstrap 5 Modal Example</title> -->
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
      <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">






<style>
        .extra-margin-top {
            margin-top: 200px; /* Adjust this value to get the desired margin */
        }



       body {
                    background-color: #e6f7ff; /* Change this to the desired background color */
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
            </div>


            <div class="dropdown">
                <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                   <!----image display in icon when user sign in--!>
                  <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">

            </div>


                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                  <!--  <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>--!>
           <li><a class="dropdown-item" href="edit"><strong>Edit</strong></a></li>

                    <li><a class="dropdown-item" href="ChangePasswordPage"><strong>Change Password</strong></a></li>

                    <li><a class="dropdown-item" href="view-profile"><strong>View</strong></a></li>

                   <!--<li><a class="dropdown-item" href="raise-complaint-view"><strong>ViewRaiseComplaint</strong></a></li>--!>

                  <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> Modal</strong></a></li>

                    <li><a class="dropdown-item" href="Raise_Complaint"><strong>RaiseComplaint</strong></a></li>
                    <li><a class="dropdown-item" href="HomePage"><strong>Logout</strong></a></li>


  <li> <a class="dropdown-item" href="view-raise-complaint"><strong>ViewRaiseComplaint</strong></a></li>

   <!-- <a class="dropdown-item" href="raise-complaint-view?complaintId=${complaint.complaintId}">---!>

                </ul>
            </div>


        </div>
    </nav>









    <!-- ******************************************************************************** --!>

    <div class="modal fade" id="ViewModal" tabindex="-1"  aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header" style="color:blue">
              <h5 class="modal-title" id="exampleModalLabel">USER PROFILE</h5>
            </div>
            <div class="profile-info">
                          <p><strong>Name:</strong> ${signUpDTO.firstName} ${signUpDTO.lastName}</p>
                          <p><strong>Email:</strong> ${signUpDTO.email}</p>
                          <p><strong>Contact Number:</strong> ${signUpDTO.contactNumber}</p>
                          <p><strong>Alternative Contact Number:</strong> ${signUpDTO.alternateContactNumber}</p>
                          <p><strong>Address:</strong> ${signUpDTO.address}</p>
                      </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
      </div>

      <!--End of View Prodile Modal -->


  <form action="/edit-profile" method="post" enctype="multipart/form-data">

      <center>
          <strong style="color:blue">
              <h3 class="mt-5   extra-margin-top">${ProfilePageMessage}</h3>
          </strong>
      </center>

  </form>






  <!---------------open modal------!>


  <!-- jQuery -->
   <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KyZXEAg3QhqLMpG8r+5RAxJ2Lq2M6K90l9K4uH/Og7Y8lTcElY+8ew0MXj0KcN+m" crossorigin="anonymous"></script>
   <!-- Popper.js -->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js" integrity="sha512-nkGz6ZD3GmL9JzNTCwWBg0oMNJfOZr0bJX8DJw75o8/ooEGQF3wFtQukj/7G4QLFnPRAA5wX8cLxZc8bZq5Hcw==" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWI2wqZpNtZKaRP6Dehc3uIjzunE2O2aHQjFFxIBB7P0TLFj2e0pH/Z1fw" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
   <!-- Bootstrap Bundle with Popper -->
   <!-- Bootstrap Bundle with Popper -->
   <!--modal---!>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.2/js/bootstrap.bundle.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3fH0YdKFffV7Ck+nJw2K8fj" crossorigin="anonymous"></script>

</body>
</html>








<!---<form action="/edit-profile" method="post" enctype="multipart/form-data">
        <div class="container mt-5 mb-5 d-flex justify-content-center">
            <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
                <div class="card-body text-center">--!>
                    <!-- Profile Image --!>
                 <!--   <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile Image" class="rounded-circle" style="width: 150px; height: 150px; object-fit: cover;">--!>
                    <!-- Profile Page Message --!>
                   <!-- <strong style="color:blue"><h3>${ProfilePageMessage}</h3></strong>
                </div>--!>



                <!-- File upload input -->
              <!--  <div class="mb-3">
                    <label for="file" class="form-label">Upload Profile Image</label>
                    <input class="form-control" type="file" id="file" name="file">
                </div>
                <button type="submit" class="btn btn-primary">Upload</button>
            </div>
        </div>
    </form>--!>




