<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


<script src="/issue-management/js/passwordReset.js"></script>

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

        <!-- Display profile image when user is signed in -->
        <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">
    </div>
</nav>

<div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Change Password</center></b></h3>
    </div>
    <div class="card-body text-dark">
        <span style="color:blue"><strong>${passwordResetMessage}</strong></span>
        <span style="color:red"><strong>${passwordResetError}</strong></span>

        <form action="reset-password" method="post">
            <div class="row mb-3">
                <label for="email" class="form-label"><b>Email:</b></label>
                <input type="email" class="form-control" id="email" name="email" autocomplete="email" onblur="emailValidation()">
                <span id="emailError" style="color:red;"></span>
            </div>

            <div class="mb-3">
                <span id="oldPasswordError" style="color:red;"></span><br>
                <label for="oldPassword" class="form-label"><b>Old Password:</b></label>
                <input type="password" class="form-control" id="oldPassword" name="oldPassword" autocomplete="old-password" onblur="oldPasswordValidation()"/>
            </div>

            <div class="mb-3">
                <span id="newPasswordError" style="color:red;"></span><br>
                <label for="newPassword" class="form-label"><b>New Password:</b></label>
                <input type="password" class="form-control" id="newPassword" name="newPassword" autocomplete="new-password" onblur="newPasswordValidation()">
            </div>

            <div class="mb-3">
                <span id="confirmPasswordError" style="color:red;"></span><br>
                <label for="confirmPassword" class="form-label"><b>Confirm Password:</b></label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" autocomplete="new-password" onblur="confirmPasswordValidation()">
            </div>

            <div>
                <input type="submit" value="Reset Password" class="btn btn-primary oval-btn" id="submit" disabled>
            </div>

            <div class="mb-3">
                <center><a href="SignIn.jsp" class="link-primary"><strong>SignIn Here?</strong></a></center>
            </div>
        </form>
    </div>
</div>
</body>
</html>
