<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View Raised Complaints</title>
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

        <!-- Display profile image when user is signed in -->
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">

    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="card">
        <div class="card-header">
            <h3><b>View Raised Complaint</b></h3>
        </div>
        <div class="card-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Serial Number</th>
                        <th>ID</th>
                        <th>Complaint Type</th>
                        <th>Country</th>
                        <th>State</th>
                        <th>City</th>
                        <th>Area</th>
                        <th>Address</th>
                        <th>Description</th>
                        <th>Edit</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="viewRaiseComplaint" items="${viewRaiseComplaints}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${viewRaiseComplaint.complaintId}</td>
                            <td>${viewRaiseComplaint.complaintType}</td>
                            <td>${viewRaiseComplaint.country}</td>
                            <td>${viewRaiseComplaint.state}</td>
                            <td>${viewRaiseComplaint.city}</td>
                            <td>${viewRaiseComplaint.area}</td>
                            <td>${viewRaiseComplaint.address}</td>
                            <td>${viewRaiseComplaint.description}</td>


                           <td><a href="edit-complaint/${viewRaiseComplaint.complaintId}">Edit</a></td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
