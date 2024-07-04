<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/issue-management/js/signup.js"></script>
</head>
<body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
                </a>
                <a class="navbar-brand text-white" href="index.jsp"><b>Home</b></a>

                    <a class="navbar-brand text-white" href="SignIn.jsp"><b>SignIn</b></a>

            </div>
        </div>
    </nav>

    <div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
        <div class="card-header">
            <h3><b><center>Sign Up </center></b></h3>
        </div>
        <div class="card-body text-dark">


            <form action="sign-up" method="post">


                <div class="text-primary"><b>${msg}</b></div>
                <!--<span style="color:red"><h4>${failedMsg}</h4></span>-->

                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>

                <div class="row mb-3">
                    <span id="firstNameError"></span><br>
                    <label for="firstName" class="form-label"><b>First Name:</b></label>
                    <input type="text" class="form-control" id="firstName" onblur="firstNameValidation()" name="firstName" value="${signUpDTO.firstName}">
                </div>
                <div class="row mb-3">
                    <span id="lastNameError"></span><br>
                    <label for="lastName" class="form-label"><b>Last Name:</b></label>
                    <input type="text" class="form-control" id="lastName" onblur="lastNameValidation()" name="lastName" value="${signUpDTO.lastName}">
                </div>

                <div class="row mb-3">
                    <span id="emailError" style="color:red"></span><br>
                    <label for="email" class="form-label"><b>Email:</b></label>
                    <input type="email" class="form-control" id="email" onblur="emailValidation()" onchange="emailAjaxValidation()"  name="email" value="${signUpDTO.email}">
                </div>



                <div class="row mb-3">
                    <span id="contactNumberError"></span><br>
                    <label for="contactNumber" class="form-label"><b>Contact Number:</b></label>
                    <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()" onchange="contactNumberAjaxValidation()" name="contactNumber" value="${signUpDTO.contactNumber}">
                </div>


                <div class="row mb-3">
                    <span id="altContactNbrError"></span><br>
                    <label for="alternateContactNumber" class="form-label"><b>Alternative Contact Number:</b></label>
                    <input type="tel" class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()" name="alternateContactNumber" value="${signUpDTO.alternateContactNumber}">
                </div>


                <div class="mb-3">
                    <span id="addressError"></span><br>
                    <b>Address</b>
                    <textarea class="form-control" id="address" style="height: 100px" name="address" onblur="addressValidation()">${signUpDTO.address}</textarea>
                </div>


                <div>
                    <span id="agreeError"></span>
                    <label for="agree" class="list-group-item">
                        <input name="agree" id="agree" onchange="agreeValidation()" class="form-check-input me-1" type="checkbox" value="agree" ${signUpDTO.agree eq 'agree' ? 'checked' : ''}>
                        <b>Agree</b>
                    </label>
                </div><br>


                <div>
                    <input type="submit" id="submit" value="Apply"  >
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

                        request.open("GET", "http://localhost:8082/issue-management/validateEmail/" + email);
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
           request.open("GET", "http://localhost:8082/issue-management/validateContactNumber/" + contactNumber);
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
