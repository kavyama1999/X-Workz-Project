<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="/IssuesManagement/js/login.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
        function disableButton() {
            var accountLocked = "${accountLocked}";
            if (accountLocked === "true") {
                document.getElementById("signInButton").disabled = true;
            }
        }
        window.onload = disableButton;
    </script>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-white" href="index.jsp"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="card border-dark container mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Login Page</center></b></h3>
    </div>

    <div class="card-body text-dark">



        <span style="color:green"><h2>${wlcm}</h2></span>
        <span style="color:red">${error}</span>



        <form action="login" method="post">

            <div class="row mb-3">
            <span id="emailError" style="color:red;"></span><br>
                <label for="email" class="form-label"><b>Email:</b></label>
                <input type="email" class="form-control" id="email" name="email" value="" onblur="emailValidation">
            </div>

            <div class="mb-3">
                        <span id="passwordError" style="color:red;"></span><br>

                <label for="password" class="form-label"><b>Password:</b></label>
                <input type="password" class="form-control" id="password" name="password" value="" onblur="passwordValidation">
            </div>

            <div>
                <input type="submit"  value="Login" class="btn btn-primary" id="signInButton" >
            </div>
        </form>

    </div>
</div>
</body>
</html>
