<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
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
        </div>
    </div>
</nav>

<div class="card border-dark container  w-25 mt-5 mb-5 justify-content-center">
    <div class="card-header">
        <h3><b><center>Forgot Password</center></b></h3>
    </div>

    <div class="card-body text-dark">
        <span style="color:blue">${resetMessage}</span>
        <span style="color:red">${resetError}</span>

        <form action="forgot-password" method="post">
            <div class="row mb-3">
                <label for="email" class="form-label"><b>Email:</b></label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div>
                <input type="submit" value="Reset Password" class="btn btn-primary">
            </div>
        </form>
    </div>
</div>
</body>
</html>
