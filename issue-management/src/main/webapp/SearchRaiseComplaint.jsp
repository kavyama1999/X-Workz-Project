<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Form</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .complaints-table {
        width: 100%;
        border-collapse: collapse;
    }
    .complaints-table th, .complaints-table td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    .complaints-table th {
        background-color: #f2f2f2;
        font-weight: bold;
    }
    .complaints-table td:nth-child(1) { width: 5%; }
    .complaints-table td:nth-child(2) { width: 10%; }
    .complaints-table td:nth-child(3) { width: 15%; }
    .complaints-table td:nth-child(4) { width: 10%; }
    .complaints-table td:nth-child(5) { width: 10%; }
    .complaints-table td:nth-child(6) { width: 10%; }
    .complaints-table td:nth-child(7) { width: 10%; }
    .complaints-table td:nth-child(8) { width: 15%; }
    .complaints-table td:nth-child(9) { width: 15%; }
</style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <!-- Add your logo here -->
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <!-- End of logo -->
            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
        </div>
    </div>
</nav>

<div class="container mt-5 w-100 mb-5 d-flex justify-content-center">
    <div class="card p-4">
        <div class="card-body">
            <form action="CountrySearched" method="post">
                <h3><b>Raise Complaint</b></h3>
                <div class="row mb-3">
                    <span id="complaintTypeError"></span>
                    <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                    <select class="form-select custom-select-width" id="complaintType" name="complaintType" required>
                        <option value="0" ${countryDTO.complaintType == null ? 'selected' : ''}>Select</option>
                        <option value="Electric issue" ${countryDTO.complaintType == 'Electric issue' ? 'selected' : ''}>Electric issue</option>
                        <option value="Water Supply" ${countryDTO.complaintType == 'Water Supply' ? 'selected' : ''}>Water Supply</option>
                        <option value="Network Problem" ${countryDTO.complaintType == 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                        <option value="System Problem" ${countryDTO.complaintType == 'System Problem' ? 'selected' : ''}>System Problem</option>
                        <option value="Water Problem" ${countryDTO.complaintType == 'Water Problem' ? 'selected' : ''}>Water Problem</option>
                    </select><br>
                </div>
                <div>
                    <input type="submit" id="submit" value="Submit">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- for table to display -->

<div class="container mt-5 mb-5 d-flex justify-content-center">
    <div class="card p-4">
        <div class="card-body">
            <div class="text-primary">${CountryName}</div>
            <table class="table complaints-table">
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${complaintType}" var="complaint" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${complaint.complaintId}</td>
                            <td>${complaint.complaintType}</td>
                            <td>${complaint.country}</td>
                            <td>${complaint.state}</td>
                            <td>${complaint.city}</td>
                            <td>${complaint.area}</td>
                            <td>${complaint.address}</td>
                            <td>${complaint.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
