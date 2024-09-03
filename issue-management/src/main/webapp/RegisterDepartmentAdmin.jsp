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

   <script src="/issue-management/js/departmentAdmin.js"></script>




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
            <h3><b><center>Register Department Admin </center></b></h3>
        </div>
        <div class="card-body text-dark">


            <form action="add-department-admin" method="post">


               <div class="text-success"><strong>${msg}</strong></div>

               <span style="color:red"><strong>${errorMsg}</strong></span>

<!--to shows Server side validation errors--!>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>

                <div class="row mb-3">
                    <label for="adminName" class="form-label "><b>DepartmentAdminName:</b></label>
                    <div class="input-group">
                     <span id="adminNameError"></span><br>

                        <span class="input-group-text" id="basic-addon1"><i class="fas fa-user"></i></span>
                        <input type="text" class="form-control" id="adminName" onblur="adminNameValidation()" name="adminName" placeholder="Enter name">
                    </div>
                </div><br>



<!--<div class="row mb-3">
                    <span id="departmentNameError"></span>
                    <label for="complaintType" class="form-label"><b>DepartmentName:</b></label>
                   <select class="form-select custom-select-width" id="complaintType" name="departmentName" onblur="departmentNameValidation()">
                       <option value="0" ${countryDTO.complaintType == null ? 'selected' : ''}>Select</option>
                       <option value="Electric" >Electric</option>
                       <option value="Network" ${countryDTO.complaintType == 'Network' ? 'selected' : ''}>Network</option>
                       <option value="Water" ${countryDTO.complaintType == 'Water' ? 'selected' : ''}>Water</option>
                       <option value="System" ${countryDTO.complaintType == 'System' ? 'selected' : ''}>System</option>
                       <option value="Gas Leakage" ${countryDTO.complaintType == 'Gas leakage' ? 'selected' : ''}>Gas leakage</option>
                   </select><br>

                </div>---!>





<div class="mb-3">
    <span id="departmentNameError"></span>
    <label for="complaintType" class="form-label"><b>DepartmentName:</b></label>
    <select class="form-select" name="departmentName" id="complaintType" onblur="departmentNameValidation()">
        <option value="" disabled selected>Select</option>
        <c:forEach var="department" items="${departments}">
            <option value="${department.departmentName}">${department.departmentName}</option>
        </c:forEach>
    </select>
</div>





           <div class="row mb-3">
                <span id="emailError" style="color:red;"></span><br>
                <label for="email" class="form-label"><b>Email:</b></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" id="email" onblur="emailValidation()" onchange="emailAjaxValidation()"  name="email" placeholder="Enter email" >

                </div>
            </div>



        <div class="row mb-3">
              <span id="contactNumberError"></span><br>

                    <label for="contactNumber" class="form-label"><b>Contact Number:</b></label>
                  <div class="input-group">
                <span class="input-group-text"><i class="fas fa-phone"></i></span>
                <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()" onchange="contactNumberAjaxValidation()" name="contactNumber" placeholder="Enter contact number">

             </div>
          </div>



               <div class="row mb-3">
                   <span id="altContactNbrError"></span><br>
                   <label for="alternateContactNumber" class="form-label"><b>Alternative Contact Number:</b></label>
                   <div class="input-group">
                       <span class="input-group-text"><i class="fas fa-phone-alt"></i></span>
                       <input type="tel" class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()" name="alternateContactNumber" placeholder="Enter alternativecontactnumber">
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
                <center>    <input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn" disabled  > </center>
                </div>


<div class="mb-3">
        <center>  <p> Have already have account?     <a href="sum-Admin-page" class="link-primary"><strong>LogIn Here?</strong></a></p></center>
            </div>

            </form>


        </div>

    </div>




<script>

<!-- ajax email validation--!>

     function emailAjaxValidation() {
           console.log("Validate email");
          let email = document.getElementById("email").value;
          console.log(email);
          let error = document.getElementById("emailError");
          if(email== "")
          {
          document.getElementById("emailError").innerHTML="Please Enter Valid email";

          }
          else
          {

              const request = new XMLHttpRequest();

              request.open("GET", "http://localhost:8082/issue-management/subAdminValidateEmail/" + email);
              request.send();
              console.log(request);
              request.onload = function () {
                  var ref = this.responseText;
                  console.log(ref);
                  error.innerHTML = ref;

                    <!--enable and disable submit button for ajax_email validation--!>

                            if (ref === "") {
                            fieldsChecks["email"] = true;
                           } else {
                           fieldsChecks["email"] = false;
                           }

                           validateAndEnableSubmit();
                           }
                        }

}

   function contactNumberAjaxValidation() {
           console.log("Validate contact number");
           let contactNumber = document.getElementById("contactNumber").value;
           console.log(contactNumber);
           let error = document.getElementById("contactNumberError");

           if(contactNumber == "")

           {
           document.getElementById("contactNumberError").innerHTML="Please enter valid contactNumber";
           }

           else

           {
           const request = new XMLHttpRequest();
           request.open("GET", "http://localhost:8082/issue-management/subAdminValidateContactNumber/" + contactNumber);
           request.send();
           console.log(request);
           request.onload = function () {
               var ref = this.responseText;
               console.log(ref);
               error.innerHTML = ref;

               // Enable and disable submit button for AJAX contact number validation
               if (ref === "") {
                   fieldsChecks["contactNumber"] = true;
               } else {
                   fieldsChecks["contactNumber"] = false;
               }

               validateAndEnableSubmit();
           }
           request.onerror = function () {
                       console.error("Request failed");
                       error.innerHTML = "<span style='color:red;'>Validation failed. Please try again.</span>";
                       fieldsChecks["contactNumber"] = false;
                       validateAndEnableSubmit();
                   }
       }
}
</script>




</body>
</html>
