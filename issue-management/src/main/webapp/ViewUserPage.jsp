<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Profile View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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

    <div class="container mt-5">
        <h2>User Profile</h2>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Name: ${signUpDTO.firstName} ${signUpDTO.lastName}</h5>
                <p class="card-text">Email: ${signUpDTO.email}</p>
                <p class="card-text">Contact Number: ${signUpDTO.contactNumber}</p>
                <p class="card-text">Alternative Contact Number: ${signUpDTO.alternateContactNumber}</p>
                <p class="card-text">Address: ${signUpDTO.address}</p>
            </div>
        </div>
    </div>
</body>
</html>
