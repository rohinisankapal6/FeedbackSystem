<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
            max-width: 400px;
            min-height: 400px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #343a40;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            color: #495057;
        }

        .form-control {
            border-radius: 5px;
            border: 1px solid #ced4da;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            width: 100%;
        }

        .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }

        .alert-danger {
            margin-bottom: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <jsp:include page="./adminheader.jsp" />

    <div class="container">
        <h1>Admin Login</h1>
        <c:if test="${not empty loginError}">
            <div class="alert alert-danger">${loginError}</div>
        </c:if>
        <form:form action="adminLogin" method="post" modelAttribute="adminCommand">
            <div class="form-group">
                <form:label path="username">Username:</form:label>
                <form:input type="text" class="form-control" path="username" required="true" placeholder="Enter your username"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password:</form:label>
                <form:input type="password" class="form-control" path="password" required="true" placeholder="Enter your password"/>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form:form>
    </div>

<jsp:include page="./footer.jsp" />
</body>
</html>