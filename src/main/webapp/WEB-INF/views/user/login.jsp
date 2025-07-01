<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        .form-control {
            border-radius: 5px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .form-control:focus {
            border-color: #66afe9;
            outline: 0;
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102,175,233,.6);
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            border-radius: 5px;
            padding: 12px 20px;
            font-size: 16px;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .btn-default {
            border-radius: 5px;
            padding: 12px 20px;
            font-size: 16px;
        }

        .alert-danger {
            margin-bottom: 20px;
            border-radius: 5px;
            padding: 15px;
        }

        .validation-message {
            font-size: 12px;
            margin-top: 5px;
            display: block;
        }

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            let usernameValid = false;
            let passwordValid = false;

            function validateForm() {
                return usernameValid && passwordValid;
            }

            $("#username").keyup(function() {
                let username = $(this).val();
                let usernameValidation = $("#usernameValidation");

                if (username.trim() === "") {
                    usernameValidation.text("Username cannot be empty.").css("color", "red");
                    usernameValid = false;
                } else {
                    usernameValidation.text(""); // Clear message
                    usernameValid = true;
                }
                $("#loginButton").prop("disabled", !validateForm());
            });

            $("#password").keyup(function() {
                let password = $(this).val();
                let passwordValidation = $("#passwordValidation");

                if (password.trim() === "") {
                    passwordValidation.text("Password cannot be empty.").css("color", "red");
                    passwordValid = false;
                } else {
                    passwordValidation.text(""); // Clear message
                    passwordValid = true;
                }
                $("#loginButton").prop("disabled", !validateForm());
            });

            // Initial disable of the login button
            $("#loginButton").prop("disabled", true);
        });
    </script>
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>Login</h1>
        <c:if test="${not empty loginError}">
            <div class="alert alert-danger">${loginError}</div>
        </c:if>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required="required">
                <span id="usernameValidation" class="validation-message"></span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required="required">
                <span id="passwordValidation" class="validation-message"></span>
            </div>
            <button type="submit" class="btn btn-primary" id="loginButton">Login</button>
            <a href="register" class="btn btn-default">Register</a>
        </form>
    </div>

<jsp:include page="../footer.jsp" />
</body>
</html>