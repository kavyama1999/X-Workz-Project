<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Submit Feedback</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3>Submit Feedback</h3>
    <form action="/submit-feedback" method="post">
        <input type="hidden" name="complaintId" value="${complaintId}">
        <div class="mb-3">
            <label for="feedbackText" class="form-label">Your Feedback</label>
            <textarea class="form-control" id="feedbackText" name="feedbackText" rows="5" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
