<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            margin-top: 50px;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        p {
            font-size: 16px;
            line-height: 1.6;
            color: #555;
            margin-bottom: 20px;
            text-align: center;
            display: block;
        }

        .manage-users-container {
            margin-top: 20px;
        }
        .manage-feedback-container {
            margin-top: 10px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            transition: background-color 0.3s ease;
            display: inline-block;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }


        header {
            background-color: #343a40;
            color: #fff;
            padding: 10px 0;
            text-align: center;
            margin-bottom: 20px;
        }


    </style>
</head>
<body>
    <jsp:include page="./adminheader.jsp" />

    <div class="container">
        <h1>Admin Dashboard</h1>
        <p>Welcome, Admin ${admin.username}!</p>
        <div class="manage-users-container">
            <a href="<c:url value="/manageUsers" />" class="btn btn-primary">Manage Users</a>
        </div>


        <div class="manage-feedback-container">
             <a href="<c:url value="/manageFeedback" />" class="btn btn-primary">Manage FeedBack</a>
        </div>
    </div>

<jsp:include page="./footer.jsp" />
</body>
</html>