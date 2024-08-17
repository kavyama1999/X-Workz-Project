<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
   <!-- <title>View Raised Complaints</title>
   <link rel="stylesheet"
   	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
   <link rel="stylesheet"
   	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
   <link
   	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
   	rel="stylesheet">
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   <script
   	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>--!>


   	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
            </a>
            <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
            <a class="navbar-brand text-white" href="Profile.jsp"><b>Profile</b></a>
        </div>



<div class="dropdown">
                <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                   <!----image display in icon when user sign in--!>
 <!--                 <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">--!>

            </div>


                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

         <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>

       <li><a class="dropdown-item" href="ChangePasswordPage"><strong>Change Password</strong></a></li>

      <li><a class="dropdown-item" href="view-profile"><strong>UserView</strong></a></li>


     <!---<li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> Modal</strong></a></li>--!>

   <li><a class="dropdown-item" href="Raise_Complaint"><strong>RaiseComplaint</strong></a></li>

        <li><a class="dropdown-item" href="HomePage"><strong>Logout</strong></a></li>



   <!-- <a class="dropdown-item" href="raise-complaint-view?complaintId=${complaint.complaintId}">---!>

                </ul>
            </div>

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
                        <th>ComplaintId</th>
                        <th>Allocate Employee</th>
                        <th>Status</th>
                        <th>Submit</th>



                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="particularData" items="${particularDepartment}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${particularData.complaintId}</td>
                            <td>${particularData.complaintType}</td>
                            <td>${particularData.country}</td>
                            <td>${particularData.state}</td>
                            <td>${particularData.city}</td>
                            <td>${particularData.area}</td>
                            <td>${particularData.address}</td>
                            <td>${particularData.description}</td>
                            <th>${particularData.signUpDTO.id}</th>




           <td>
              <select class="form-select status-select" name="departmentId">
         <option value="Select" disabled selected>Select</option>

                  <c:forEach var="department" items="${departments}">
                     <!-- <option value="${department.id}" ${viewRaiseComplaintUsers.complaintId == department.id ? 'selected' : ''}>${department.departmentName}</option>--!>

    <option value="${department.id}" ${viewRaiseComplaintUsers.departmentDTO != null && viewRaiseComplaintUsers.departmentDTO.id == department.id ? 'selected' : ''}>${department.departmentName}</option>

              </c:forEach>
                  </select>
              </td>



                     <td>
                      <select class="form-select status-select" name="status">
                          <option value="Select" disabled>Select</option>
                          <option value="Pending" ${viewRaiseComplaintUsers.status == 'Pending' ? 'selected' : ''}>Pending</option>
                          <option value="In Process" ${viewRaiseComplaintUsers.status == 'In Process' ? 'selected' : ''}>In Process</option>
                          <option value="Completed" ${viewRaiseComplaintUsers.status == 'Completed' ? 'selected' : ''}>Completed</option>
                      </select>
                                </td>

                         <td>
                              <button type="submit" class="btn btn-primary">Submit</button>
                          </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

