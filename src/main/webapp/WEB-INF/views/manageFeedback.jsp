<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manage Feedback</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
        }

        h1 {
            color: #343a40;
            text-align: center;
            margin-bottom: 30px;
            margin-top:auto;
        }

        .table {
            width: 100%;
            margin-bottom: 1rem;
            color: #212529;
        }

        .table th,
        .table td {
            padding: 0.75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6;
        }

        .table thead th {
            vertical-align: bottom;
            border-bottom: 2px solid #dee2e6;
            background-color: #f2f2f2;
        }

        .table tbody + tbody {
            border-top: 2px solid #dee2e6;
        }

        .table .table-bordered {
            border: 1px solid #dee2e6;
        }

        .table .table-bordered th,
        .table .table-bordered td {
            border: 1px solid #dee2e6;
        }

        .table .table-bordered thead th,
        .table .table-bordered thead td {
            border-bottom-width: 2px;
        }

        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }

        .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        .btn-xs {
            padding: .25rem .4rem;
            font-size: .875rem;
            line-height: 1.5;
            border-radius: .2rem;
        }

        .admin-header {
          background-color: #0f1056;
          color: white;
          padding: 15px;
          text-align: center;
          margin-bottom: 20px;
        }

        .admin-header h2 {
          margin: 0;
        }

        .footer {
            background-color: #f2f2f2;
            padding: 15px;
            text-align: center;
            margin-top: 20px;
            border-top: 1px solid #dee2e6;
            color: #6c757d;
        }

        .highlight-row:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="admin-header">
        <h2>Admin Panel</h2>
    </div>

    <div class="container">
        <h1>Manage Feedback</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Feedback ID</th>
                    <th>User ID</th>
                    <th>Category ID</th>
                    <th>Submission Date</th>
                    <th>Question 1</th>
                    <th>Question 2</th>
                    <th>Question 3</th>
                    <th>Question 4</th>
                    <th>Question 5</th>
                    <th>Question 6</th>
                    <th>Question 7</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${feedbackList}" var="feedback">
                    <tr class="highlight-row">
                        <td>${feedback.feedbackId}</td>
                        <td>${feedback.userId}</td>
                        <td>${feedback.categoryId}</td>
                         <td><fmt:formatDate value="${feedback.submissionDate}" pattern="yyyy-MM-dd" /></td>
                        <td>${feedback.question1Answer}</td>
                        <td>${feedback.question2Answer}</td>
                        <td>${feedback.question3Answer}</td>
                        <td>${feedback.question4Answer}</td>
                        <td>${feedback.question5Answer}</td>
                        <td>${feedback.question6Answer}</td>
                        <td>${feedback.question7Answer}</td>

                        <td>
                            <a href="<c:url value="/deleteFeedback/${feedback.feedbackId}" />" class="btn btn-danger btn-xs">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty feedbackList}">
                    <tr>
                        <td colspan="12">No feedback found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <jsp:include page="./footer.jsp" />
</body>
</html>