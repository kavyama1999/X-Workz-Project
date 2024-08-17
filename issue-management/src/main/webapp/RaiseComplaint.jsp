
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">


     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


        <script src="/issue-management/js/raiseComplaint.js"></script>

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
                <a class="navbar-brand text-white" href="HomePage"><b>Home</b></a>
            <a class="navbar-brand text-white" href="Profile.jsp"><b>Profile</b></a>
            </div>


<div class="dropdown">
                <div class="dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                   <!----image display in icon when user sign in--!>
                  <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle">

            </div>


                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">

                    <li><a class="dropdown-item" href="edit?email=${signUpDTO.email}"><strong>Edit</strong></a></li>

                    <li><a class="dropdown-item" href="ChangePasswordPage"><strong>Change Password</strong></a></li>

                    <li><a class="dropdown-item" href="view-profile"><strong>UserView</strong></a></li>


                 <!-- <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal" ><strong> Modal</strong></a></li>--!>




  <li> <a class="dropdown-item" href="view-raise-complaint"><strong>ViewRaiseComplaint</strong></a></li>

  <li><a class="dropdown-item" href="HomePage"><strong>Logout</strong></a></li>


   <!-- <a class="dropdown-item" href="raise-complaint-view?complaintId=${complaint.complaintId}">---!>

                </ul>
            </div>



        </div>

    </nav>

    <div class="card border-dark container w-25 mt-5 mb-5 justify-content-center">
        <div class="card-header">
            <h3><b><center>Raise Complaints</center></b></h3>
        </div>
        <div class="card-body text-dark">

        <span style="color:green"><h2>${raiseComplaintMsg}</h2></span>


            <form action="raise-complaint" method="post">

<!--For server side validation this is used--!>
                <div class="text-success"><strong>${msg}</strong></div>
                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}<br>
                    </c:forEach>
                </span>

                <div class="row mb-3">
                    <span id="complaintTypeError"></span>
                    <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                    <select class="form-select custom-select-width" id="complaintType" name="complaintType" required>
                        <option value="0" ${countryDTO.complaintType == null ? 'selected' : ''}>Select</option>
                        <option value="Electric" ${countryDTO.complaintType == 'Electric' ? 'selected' : ''}>Electric</option>
                        <option value="Network" ${countryDTO.complaintType == 'Network' ? 'selected' : ''}>Network</option>
                        <option value="Water" ${countryDTO.complaintType == 'Water' ? 'selected' : ''}>Water</option>
                        <option value="Gas Leakage" ${countryDTO.complaintType == 'Gas Leakage' ? 'selected' : ''}>Gas Leakage</option>
                        <option value="System" ${countryDTO.complaintType == 'System' ? 'selected' : ''}>System</option>

                    </select><br>
                </div>

<!---<div class="row mb-3">
                    <span id="complaintTypeError"></span>
                    <label for="complaintType" class="form-label"><b>DepartmentName:</b></label>
                    <select class="form-select custom-select-width" id="complaintType" name="complaintType" required>
                        <option value="0" ${countryDTO.complaintType == null ? 'selected' : ''}>Select</option>
                        <option value="Electric " ${countryDTO.complaintType == 'Electric' ? 'selected' : ''}>Electric </option>
                        <option value="Network" ${countryDTO.complaintType == 'Network' ? 'selected' : ''}>Network</option>
                        <option value="Water" ${countryDTO.complaintType == 'Water' ? 'selected' : ''}>Water</option>
                        <option value="System" ${countryDTO.complaintType == 'System' ? 'selected' : ''}>System</option>
                 <option value="Gas Leakage" ${countryDTO.complaintType == 'Gas leakage' ? 'selected' : ''}>Gas leakage</option>

                    </select><br>
                </div>----!>

                <!----Country ---!>

                <div class="row mb-3">
                    <span id="countryNameError"></span>
                    <label for="countryName" class="form-label"><b>Country:</b></label>
                    <select class="form-select custom-select-width" id="countryName" name="country"  placeholder="Enter country" required>
                        <!-- Countries will be loaded here by JavaScript -->
                    </select><br>
                </div>

                <!----State ---!>

                <div class="row mb-3">
                    <span id="stateNameError"></span>
                    <label for="state" class="form-label"><b>State:</b></label>
                    <select class="form-select custom-select-width" id="state" name="state"  required >
                        <!-- States will be loaded here by JavaScript -->
                    </select><br>
                </div>

                <!----City ---!>

                <div class="row mb-3">
                    <span id="cityNameError"></span>
                    <label for="city" class="form-label"><b>City:</b></label>
                    <select class="form-select custom-select-width" id="city" name="city" placeholder="Enter city" required>
                        <!-- Cities will be loaded here by JavaScript -->
                    </select><br>
                </div>


<div class="row mb-3">
                    <span id="areaError"></span><br>
                    <label for="area" class="form-label"><b>Area:</b></label>
                    <input type="text" class="form-control" id="area" name="area" placeholder="Enter area" required>
                </div>



<div class="mb-3">
                    <span id="errorAddress"></span><br>
                    <b>Address</b>
                    <label for="address" class="form-floating"></label>
                    <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address" required>${jobFormDTO.address}</textarea>
                </div>



<div class="mb-3">
                <span id="descriptionError" class="text-danger"></span>
             <b>Description:</b>
                <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a comment here" name="description" id="description"  style="height:80px"  oninput="updateDescriptionCount()" maxlength="300" onblur="validateDescription()"  required>${complaint.description}</textarea>
                    <label for="description">Description</label>
                </div>
            </div>


                <div>
                    <span id="agreeError"></span>
                    <label for="agree" class="list-group-item">
                        <input name="agree" id="agree" onchange="agreeValidation()" class="form-check-input me-1" type="checkbox" value="agree" ${signUpDTO.agree eq 'agree' ? 'checked' : ''} required>
                        <b>Agree</b>
                    </label>
                </div><br>

                <div>
                    <center><input type="submit" id="submit" value="Apply" class="btn btn-primary oval-btn"></center>
                </div>

                <!--<div class="mb-3">
                    <center><a href="ViewRaiseComplaint.jsp" class="link-primary"><strong>ViewRaiseComplaint</strong></a></center>
                </div>--!>
            </form>
        </div>
    </div>

    <!-- Including the JavaScript file after the DOM is loaded

           <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybBl5f7lxG7bI1Qags2ndFL0BbvhXTEX8EN2giD6S3lfjF5/T" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-I9QiN/BaMIn1+XJj1/rXilAdBfWzBfT+X8Q+j5FZk5TGAa+lUxl5jXU4rL9Tbt8/" crossorigin="anonymous"></script>--!>
</body>
</html>
