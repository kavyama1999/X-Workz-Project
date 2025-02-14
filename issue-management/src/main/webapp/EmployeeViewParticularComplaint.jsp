<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</head>


<style>
    .custom-border {
        border: 5px solid black;
        width:100%;
    }
</style>
<body>


<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
            <a class="navbar-brand text-white" href="Profile.jsp"><b>Profile</b></a>
            <a class="navbar-brand text-white" href="SubmitUserFeedback.jsp"><b>UserFeedBack</b></a>

        </div>

        <div class="dropdown">
            <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                <!-- Image Display when User Signs In -->
            </div>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>
                <li><a class="dropdown-item" href="ChangePasswordPage"><strong>Change Password</strong></a></li>
                <li><a class="dropdown-item" href="view-profile"><strong>UserView</strong></a></li>
                <li><a class="dropdown-item" href="Raise_Complaint"><strong>RaiseComplaint</strong></a></li>
                <li><a class="dropdown-item" href="HomePage"><strong>Logout</strong></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5 mb-5">
    <div class="card ">
        <div class="card-header">
            <h3><b>View Raised Complaint</b></h3>

        <span style="color:green"><strong>${success}</strong></span>
        <span style="color:red"><strong>${errorMessage1}</strong></span>
        </div>
        <div class="card-body ">
            <table class="table table-bordered ">
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
                        <th>ComplaintId</th>
                        <th>Assigned_Employee_Name</th>
                        <th style="width:100%">Status</th>
                        <th style="width:200px">Complaint-Status</th>
                        <th>Submit</th>



                    </tr>

                </thead>
                <tbody>
                    <!-- Ensure particularComplaint is properly passed from the Controller -->
                    <c:forEach var="particularData" items="${particularDepartment1}" varStatus="status">
                        <tr style="width:200%">
                            <td>${status.index + 1}</td>
                            <td>${particularData.complaintId}</td>
                            <td>${particularData.complaintType}</td>
                            <td>${particularData.country}</td>
                            <td>${particularData.state}</td>
                            <td>${particularData.city}</td>
                            <td>${particularData.area}</td>
                            <td>${particularData.address}</td>
                            <td>${particularData.description}</td>
                            <td>${particularData.signUpDTO.id}</td>
                            <td>${particularData.employeeDTO.employeeName}</td>
                        <td style="width:100px">${particularData.status}</td>

                <td style="width:100%">

                  <form action="update-complaintId" method="post">
                      <input type="hidden" name="complaintId" value="${particularData.complaintId}">
                      <select class="form-select status-select" name="complaintStatus" required>
                          <option value="Select">Select</option>
                          <option value="Pending" ${particularData.complaintStatus == 'Pending' ? 'selected' : ''}>Pending</option>
                          <option value="In Progress" ${particularData.complaintStatus == 'In Progress' ? 'selected' : ''}>In Progress</option>
                          <option value="Completed" ${particularData.complaintStatus == 'Completed' ? 'selected' : ''}>Completed</option>
                      </select>
                  </td>

                           <td>
                                 <button type="submit" class="btn btn-primary">Submit</button>
                           </td>
                  </form>



                </tr>




                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


