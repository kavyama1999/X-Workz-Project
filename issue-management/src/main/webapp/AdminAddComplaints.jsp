<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- To include the Font Awesome library in your HTML and then use the appropriate classes to add the icons. -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    .oval-btn {
        border-radius: 40px; /* Adjust the value as needed for an oval shape */
        padding: 20px 20px;  /* Adjust the padding to control the button size */
    }


        .bold-text {
            font-weight: bold; /* Makes the text bold */
            font-size: 1.5em;  /* Adjust the size as needed */
        }

    body {
                    background-color: white; /* Change this to the desired background color */
                }
</style>

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
            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
        </div>


    </div>
</nav>

<div class="card border-dark container  w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Add Complaint Form</center></b></h3>
    </div>

    <div class="card-body text-dark">

        <span style="color:green"><h2>${msg}</h2></span>
        <span style="color:red">${error}</span>

        <form action="add-department" method="post">
            <div class="row mb-3">
                <span id="emailError" style="color:red;"></span><br>
                <label for="departmentType" class="form-label"><b>Department Name:</b></label>
                <div class="input-group">
                    <span class="input-group-text"></i></span>
                    <input type="text" class="form-control" id="departmentType" name="departmentType" placeholder="Enter department name" value="" required>
                </div>
            </div>


            <div class="mb-3">
                <span id="passwordError" style="color:red;"></span><br>
                <label for="address" class="form-label"><b>Address:</b></label>
                <div class="input-group">
                    <span class="input-group-text"></i></span>
                    <input type="text" class="form-control" id="address" name="address" placeholder="Enter address" value=""  required>
                </div>
            </div>



            <div class="d-flex justify-content-center mt-3">
                <input type="submit" value="SignIn" class="btn btn-dark oval-btn bold-text" id="signInButton">
            </div>
        </form>
    </div>
</div>
</body>
</html>
