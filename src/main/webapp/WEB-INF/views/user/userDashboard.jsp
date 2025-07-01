<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }


        .header {
            background-color: #343a40;
            color: white;
            padding: 15px 0;
            margin-bottom: 20px;
            text-align: center;
        }

        .header a {
          color: white;
          text-decoration: none;
        }

        .container {
            margin-top: 10px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #343a40;
            margin-bottom: 20px;
            text-align: center;
        }

        h2 {
            color: #343a40;
            margin-top: 30px;
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 15px;
            color: #495057;
            color:blue;
            text-align: center;
             font-size: 20px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }

        .btn-default {
            background-color: #6c757d;
            border-color: #6c757d;
            color: white;
        }

        .btn-default:hover {
            background-color: #5a6268;
            border-color: #545b62;
            color: white;
        }

        .table {
            margin-top: 20px;
            background-color: #f2f4f8;
        }

        .table thead th {
            background-color: #e9ecef;
            border-bottom: 2px solid #dee2e6;
        }

        .table tbody tr:nth-of-type(odd)
            background-color: #f9f9f9;
        }


        .btn-info, .btn-warning {
            padding: 0.5rem 1rem;
            font-size: 1rem;
            line-height: 1.5;
        }

        .btn-info {
            background-color: #17a2b8;
            border-color: #17a2b8;
            color: white;
        }

        .btn-info:hover {
            background-color: #138496;
            border-color: #117a8b;
            color: white;
        }

        .btn-warning {
            background-color: #ffc107;
            border-color: #ffc107;
        }

        .btn-warning:hover {
            background-color: #e0a800;
            border-color: #d39e00;
        }

        .btn-xs {
            padding: 0.25rem 0.5rem;
            font-size: 0.75rem;
            line-height: 1.5;
            border-radius: 0.2rem;
        }

        .no-feedback {
          text-align: center;
          font-style: italic;
          color: #6c757d;
        }

    </style>
</head>
<body>
   <jsp:include page="../header.jsp" />


    <div class="container">
        <h1>Welcome, ${user.fullName}</h1>
        <p>Driving Improvement with Feedback</p>
        <p>
            <a href="<c:url value="/feedback/submit" />" class="btn btn-primary">Feedback Now</a>
            <a href="<c:url value="/userProfile" />" class="btn btn-default">View Profile</a>
        </p>

        <h2>Your Feedback</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Question 1</th>
                    <th>Question 2</th>
                    <th>Question 3</th>
                    <th>Question 4</th>
                    <th>Question 5</th>
                    <th>Question 6</th>
                    <th>Question 7</th>
                    <th>Submission Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${feedbackList}" var="feedback">
                    <tr>
                        <td>${feedback.categoryName}</td>
                        <td>${feedback.question1Answer}</td>
                        <td>${feedback.question2Answer}</td>
                        <td>${feedback.question3Answer}</td>
                        <td>${feedback.question4Answer}</td>
                        <td>${feedback.question5Answer}</td>
                        <td>${feedback.question6Answer}</td>
                        <td>${feedback.question7Answer}</td>
                        <td>${feedback.submissionDate}</td>
                        <td>
                            <a href="<c:url value="/feedback/view/${feedback.feedbackId}" />" class="btn btn-info">View</a>
                            <a href="<c:url value="/feedback/edit/${feedback.feedbackId}" />" class="btn btn-warning">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty feedbackList}">
                    <tr>
                        <td colspan="10" class="no-feedback">No feedback submitted yet.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>