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
            border-radius: 50px;
            padding: 10px 20px;
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
               <!-- <a class="navbar-brand text-white" href="SignIn.jsp"><b>SignIn</b></a>--!>
                <a class="navbar-brand text-white" href="view-profile"><b>View</b></a>



            </div>

            <li class="nav-item">

            <!--image display in right side icon--- for when i new user signIn based user signIn it will display image of user--!>

            <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="80" height="80" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">
            </li>

        </div>
    </nav>

    <div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
        <div class="card-header">
            <h3><b><center>Edit Profile </center></b></h3>
        </div>
        <div class="card-body text-dark">

            <form action="edit-profile" method="post" enctype="multipart/form-data">


        <span style="color:red">${errorMessageProfile}</span>
        <span style="color:green">${message}</span>
        <span style="color:red">${errorMessage}</span>
 <span style="color:green"><strong>${profileUploadMsg}</strong></span>



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
                    <input type="email" class="form-control" id="email" name="email" value="${editSignUpDTO.email}" disabled>
                </div>
                <div class="row mb-3">
                    <span id="contactNumberError"></span><br>
                    <label for="contactNumber" class="form-label"><b>Contact Number:</b></label>
                    <input type="tel" class="form-control" id="contactNumber" onblur="contactNumberValidation()" name="contactNumber" value="${editSignUpDTO.contactNumber}">
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
                <div class="mb-3">
                    <label for="file" class="form-label text-dark">Choose File</label>
                    <input type="file" class="form-control" name="file" id="file">
                </div>
                <div>
                    <center><input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn"></center>
                </div>
                <div class="mb-3">
                    <center><p>Have an account? <a href="SignIn.jsp" class="link-primary"><strong>SignIn Here?</strong></a></p></center>
                </div>
            </form>
        </div>
    </div>

   <!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-ND83p6+2LC9sNGvzFgiptEh0Wt3veCHpdwwvWY3Aj23FR5f4ob0C5sHbPkzJf6Hm" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-5mrLOimZlMFbbXUpiH8eAFKmKXbLqaW8GDoAWF+Q6h4Ec8Q2pSyyKhcvwwa3fznK" crossorigin="anonymous"></script>--!>


</body>
</html>
