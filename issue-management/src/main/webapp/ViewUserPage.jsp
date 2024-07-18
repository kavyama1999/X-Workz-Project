<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Profile View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f8ff; /* Light blue background */
        }
        .profile-card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
            padding: 20px;
        }
        .profile-img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
        }
        .profile-header {
            display: flex;
            align-items: center;
            gap: 20px;
        }
        .profile-header h2 {
            margin: 0;
        }
        .profile-info {
            margin-top: 20px;
        }
        .profile-info p {
            margin-bottom: 10px;
            font-size: 16px;
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
                <a class="navbar-brand text-white" href="Profile.jsp"><b>Profile</b></a>
            </div>

            <!-- Display image in icon when user sign in -->
            <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" class="profile-img rounded-circle">
        </div>
    </nav>

    <div class="container mt-5 w-25">
        <div class="profile-card mx-auto">
            <div class="profile-header">
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" class="profile-img rounded-circle">
                <h2>User Profile</h2>
            </div>
            <div class="profile-info">
                <p><strong>Name:</strong> ${signUpDTO.firstName} ${signUpDTO.lastName}</p>
                <p><strong>Email:</strong> ${signUpDTO.email}</p>
                <p><strong>Contact Number:</strong> ${signUpDTO.contactNumber}</p>
                <p><strong>Alternative Contact Number:</strong> ${signUpDTO.alternateContactNumber}</p>
                <p><strong>Address:</strong> ${signUpDTO.address}</p>
            </div>
        </div>
    </div>
</body>
</html>
