<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

   <!--<script src="/issue-management/js/departmentAdmin.js"></script>--!>




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
        .oval-btn {
            border-radius: 50px;
            padding: 10px 20px;
        }


       body {
                    background-color:white; /* e6f7ff Change this to the desired background color */
                }
    </style>


</head>
<body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
                </a>
                <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>

                    <a class="navbar-brand text-white" href="SignInPage"><b>SignIn</b></a>

            </div>


            <div class="dropdown">
                        <button class="dropdown-toggle dropdown-toggle-custom" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            Admin
               </button>
               <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
               <li><a class="dropdown-item" href="view-user-details"><strong>ViewUserDetails</strong></a></li>

               <li><a class="dropdown-item" href="View-raise-complaint"><strong>ViewRaiseComplaintDetails</strong></a></li>

              <li><a class="dropdown-item" href="add-complaints"><strong>AddComplaints</strong></a></li>


                        </ul>
                    </div>
        </div>
    </nav>

    <div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
        <div class="card-header">
            <h3><b><center> Employee Register Form </center></b></h3>
        </div>
        <div class="card-body text-dark">


            <form action="employeeData" method="post">


               <div class="text-success"><strong>${msg}</strong></div>

               <span style="color:red"><strong>${errorMsg}</strong></span>

       <!--to shows Server side validation errors--!>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>



                <div class="row mb-3">
                    <label for="adminName" class="form-label"><b>Employee Name:</b></label>
                    <div class="input-group">
                     <span id="adminNameError"></span><br>

                        <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                        <input type="text" class="form-control" id="adminName" onblur="adminNameValidation()" name="employeeName" placeholder="Enter name">
                    </div>
                </div><br>




<div class="mb-3">
    <span id="departmentNameError"></span>
    <label for="complaintType" class="form-label"><b>Select Department:</b></label>
    <select class="form-select" name="departmentName" id="complaintType" onblur="departmentNameValidation()">
        <option value="" disabled selected>Select</option>
        <c:forEach var="department" items="${departments}">
            <option value="${department.departmentName}">${department.departmentName}</option>
        </c:forEach>
    </select>
</div>




  <div class="row mb-3">
                <span id="emailError" style="color:red;"></span><br>
    <label for="email" class="form-label"><b>Email ID:</b></label>
    <div class="input-group">
        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
        <input type="email" class="form-control" id="email" onblur="emailValidation()" onchange="emailAjaxValidation()"  name="emailId" placeholder="Enter email" >
    </div>
   </div>



<div class="mb-3">
                    <span id="addressError"></span><br>
                    <label for="address" class="form-label"><b>Address</b></label>
                   <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                        <textarea class="form-control" id="address" style="height: 100px" name="address" onblur="addressValidation()"  placeholder="Enter address"></textarea>
                    </div>
                </div>



       <div class="row mb-3">
              <span id="contactNumberError"></span><br>

                    <label for="contactNumber" class="form-label"><b>Contact Number:</b></label>
                  <div class="input-group">
                <span class="input-group-text"><i class="fas fa-phone"></i></span>


        <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()" onchange="contactNumberAjaxValidation()" name="contactNumber" placeholder="Enter contactnumber" >
                </div>
       </div>




               <div class="row mb-3">
                   <span id="altContactNbrError"></span><br>
                   <label for="alternateContactNumber" class="form-label"><b>Alternative ContactNumber:</b></label>
                   <div class="input-group">
                       <span class="input-group-text"><i class="fas fa-phone-alt"></i></span>
                       <input type="tel" class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()" name="alternativeContactNumber" placeholder="Enter alternativecontactnumber">
                   </div>
               </div>



                      <div>
                       <span id="agreeError"></span>
                      <label for="agree" class="list-group-item">
                          <input name="agree" id="agree" onchange="agreeValidation()" class="form-check-input me-1" type="checkbox" value="agree" ${signUpDTO.agree eq 'agree' ? 'checked' : ''}>
                          <b>Agree</b>
                      </label>
                </div><br>


                <div>
                <center>    <input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn"   > </center>
                </div>


  <div class="mb-3">
        <center>  <p> Have already have account?  <a href="employeeLogin" class="link-primary"><strong>Please Login To get OTP to click here?</strong></a></p></center>
            </div>

            </form>


        </div>

    </div>


</body>
</html>
