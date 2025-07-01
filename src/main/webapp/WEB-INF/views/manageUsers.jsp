<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manage Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
        }

        h1 {
            color: #343a40;
            text-align: center;
            margin-bottom: 30px;
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

        /* Custom Styles */
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
            background-color: #f5f5f5; /* Light gray on hover */
            cursor: pointer;
        }


    </style>
</head>
<body>
    <div class="admin-header">
        <h2>Admin Panel</h2>
    </div>

    <div class="container">
        <h1>Manage Users</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr class="highlight-row">
                        <td>${user.userId}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>
                            <a href="<c:url value="/deleteUser/${user.userId}" />" class="btn btn-danger btn-xs">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="./footer.jsp" />
</body>
</html>