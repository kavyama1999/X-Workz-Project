<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


    <script src="/issue-management/js/edit-profile.js"></script>


<style>
        .oval-btn {
            border-radius: 50px; /* Adjust the value as needed for an oval shape */
            padding: 10px 20px;  /* Adjust the padding to control the button size */
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


            <form action="edit-profile" method="post" enctype="multipart/form-data">

<!--<form action="editprofile" method="post" onsubmit="return validateForm()"
 enctype="multipart/form-data">--!>

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
                    <input type="text" class="form-control" id="firstName" onblur="firstNameValidation()" name="firstName" value="${editSignUpDTO.firstName}">
                </div>
                <div class="row mb-3">
                    <span id="lastNameError"></span><br>
                    <label for="lastName" class="form-label"><b>Last Name:</b></label>
                    <input type="text" class="form-control" id="lastName" onblur="lastNameValidation()" name="lastName" value="${editSignUpDTO.lastName}">
                </div>

                <div class="row mb-3">
                    <span id="emailError" style="color:red"></span><br>
                    <label for="email" class="form-label"><b>Email:</b></label>
                    <input type="email" class="form-control" id="email"   name="email" value="${editSignUpDTO.email}" disabled>
                </div>



                <div class="row mb-3">
                    <span id="contactNumberError"></span><br>
                    <label for="contactNumber" class="form-label"><b>Contact Number:</b></label>
                    <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()"  name="contactNumber" value="${editSignUpDTO.contactNumber}">
                </div>


                <div class="row mb-3">
                    <span id="altContactNbrError"></span><br>
                    <label for="alternateContactNumber" class="form-label"><b>Alternative Contact Number:</b></label>
                    <input type="tel" class="form-control" id="alternateContactNumber" onblur="alternateContactNumberValidation()" name="alternateContactNumber" value="${editSignUpDTO.alternateContactNumber}">
                </div>


                <div class="mb-3">
                    <span id="addressError"></span><br>
                    <b>Address</b>
                    <textarea class="form-control" id="address" style="height: 100px" name="address" onblur="addressValidation()">${editSignUpDTO.address}</textarea>
                </div>


          <!--      <div>
                    <span id="agreeError"></span>
                    <label for="agree" class="list-group-item">
                        <input name="agree" id="agree" onchange="agreeValidation()" class="form-check-input me-1" type="checkbox" value="agree" ${editSignUpDTO.agree eq 'agree' ? 'checked' : ''}>
                        <b>Agree</b>
                    </label>
                </div><br>--!>


          <div class="mb-3">
                    <label for="file" class="form-label text-dark">Choose File</label>
                    <input type="file" class="form-control" name="file" id="file">
                </div>

                <!--<div class="mb-3">
                    <input type="submit" class="form-control btn btn-primary" style="width:100px" value="Upload" name="upload" id="upload">
                </div>--!>


<!--<form method="post" enctype="multipart/form-data" action="upload">
                    <input type="file" name="file"/>
                    <input type="submit" value="Upload file"/>
                </form>--!>


                <div>
                <center>    <input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn" disabled > </center>
                </div>


<div class="mb-3">
        <center>  <p> Have already have account?     <a href="SignIn.jsp" class="link-primary"><strong>SignIn Here?</strong></a></p></center>
            </div>

            </form>


        </div>

    </div>





</body>
</html>
