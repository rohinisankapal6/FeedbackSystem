<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Feedback</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .container {
            margin-top: 30px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        h1 {
            color: #007bff;
            text-align: center;
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 10px;
        }

        strong {
            font-weight: bold;
            color: #343a40;
        }

        .btn {
            margin-right: 10px;
        }

        .btn-warning {
            background-color: #ffc107;
            border-color: #ffc107;
            color: #212529;
        }

        .btn-warning:hover {
            background-color: #e0a800;
            border-color: #d39e00;
        }

        .btn-blue {
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
        }

        .btn-blue:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

       .btn-success-custom {
            background-color: #28a745;
            border-color: #28a745;
            color: #fff;
        }

        .btn-success-custom:hover {
            background-color: #218838;
            border-color: #1e7e34;
        }


        header, footer {
            background-color: #343a40;
            color: white;
            padding: 10px 0;
            text-align: center;
        }

        header a, footer a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
        }

        footer {
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>View Feedback</h1>
        <p><strong>Category:</strong> ${feedback.categoryName}</p>

        <p><strong>Question 1:</strong> ${feedback.question1Answer}</p>
        <p><strong>Question 2:</strong> ${feedback.question2Answer}</p>
        <p><strong>Question 3:</strong> ${feedback.question3Answer}</p>
        <p><strong>Question 4:</strong> ${feedback.question4Answer}</p>
        <p><strong>Question 5:</strong> ${feedback.question5Answer}</p>
        <p><strong>Question 6:</strong> ${feedback.question6Answer}</p>
        <p><strong>Question 7:</strong> ${feedback.question7Answer}</p>

        <p><strong>Submission Date:</strong> ${feedback.submissionDate}</p>

        <a href="<c:url value="/feedback/edit/${feedback.feedbackId}" />" class="btn btn-warning">Edit Feedback</a>
        <a href="<c:url value="/userDashboard" />" class="btn btn-blue">Back to Dashboard</a>

    </div>

<jsp:include page="../footer.jsp" />
</body>
</html>