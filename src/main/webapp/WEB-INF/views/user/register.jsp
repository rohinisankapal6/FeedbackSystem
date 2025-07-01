<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #343a40;
            font-weight: 500;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-weight: 600;
            color: #495057;
            display: block;
            margin-bottom: 3px;
        }

        .form-control {
            border-radius: 8px;
            padding: 10px;
            border: 1px solid #ddd;
            box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.075);
            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            width: 100%;
            box-sizing: border-box;
        }

        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            outline: 0;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            border-radius: 8px;
            padding: 10px 15px;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }

        .btn-default {
            background-color: #fff;
            border-color: #6c757d;
            color: #6c757d;
            border-radius: 8px;
            padding: 10px 15px;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, color 0.15s ease-in-out;
        }

        .btn-default:hover {
            background-color: #e2e6ea;
            border-color: #5a6268;
            color: #5a6268;
        }

        .alert-danger {
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 8px;
            background-color: #f2dede;
            border-color: #ebccd1;
            color: #a94442;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .button-container .btn {
            margin: 0 8px;
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
            let emailValid = false;
            let fullNameValid = false;

            function validateForm() {
                return usernameValid && passwordValid && emailValid && fullNameValid;
            }

            $("#username").keyup(function() {
                let username = $(this).val();
                let usernameValidation = $("#usernameValidation");

                if (username.length < 8) {
                    usernameValidation.text("Username must be at least 8 characters long.").css("color", "red");
                    usernameValid = false;
                } else {
                    usernameValidation.text("Username validated.").css("color", "green");
                    usernameValid = true;
                }
                $("#registerButton").prop("disabled", !validateForm());
            });

            $("#password").keyup(function() {
                let password = $(this).val();
                let passwordValidation = $("#passwordValidation");

                let hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
                let hasUpperCase = /[A-Z]/.test(password);

                if (password.length < 8 || !hasSpecialChar || !hasUpperCase) {
                    let message = "";
                    if (password.length < 8) {
                        message += "Password must be at least 8 characters long. ";
                    }
                    if (!hasSpecialChar) {
                        message += "Password must contain at least one special character. ";
                    }
                    if (!hasUpperCase) {
                        message += "Password must contain at least one uppercase letter.";
                    }

                    passwordValidation.text(message).css("color", "red");
                    passwordValid = false;
                } else {
                    passwordValidation.text("Password validated.").css("color", "green");
                    passwordValid = true;
                }
                $("#registerButton").prop("disabled", !validateForm());
            });

            $("#email").keyup(function() {
                let email = $(this).val();
                let emailValidation = $("#emailValidation");

                // Check if the email ends with "@gmail.com"
                if (!email.endsWith("@gmail.com")) {
                    emailValidation.text("Email must end with \"@gmail.com\".").css("color", "red");
                    emailValid = false;
                } else {
                    emailValidation.text("Email validated.").css("color", "green");
                    emailValid = true;
                }
                $("#registerButton").prop("disabled", !validateForm());
            });

            $("#fullName").keyup(function() {
                let fullName = $(this).val();
                let fullNameValidation = $("#fullNameValidation");

                if (fullName.trim() === "") {
                    fullNameValidation.text("Full name cannot be empty.").css("color", "red");
                    fullNameValid = false;
                } else {
                    fullNameValidation.text("").css("color", "green"); // Clear message if valid
                    fullNameValid = true;
                }
                $("#registerButton").prop("disabled", !validateForm());
            });

            // Initial disable of the register button
            $("#registerButton").prop("disabled", true);
        });
    </script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>Register</h1>
        <c:if test="${not empty registerError}">
            <div class="alert alert-danger">${registerError}</div>
        </c:if>
        <form:form action="register" method="post" modelAttribute="userCommand">
            <div class="form-group">
                <form:label path="username">Username:</form:label>
                <form:input type="text" class="form-control" path="username" required="true" />
                <span id="usernameValidation" class="validation-message"></span>
            </div>
            <div class="form-group">
                <form:label path="password">Password:</form:label>
                <form:input type="password" class="form-control" path="password" required="true" />
                <span id="passwordValidation" class="validation-message"></span>
            </div>
            <div class="form-group">
                <form:label path="email">Email:</form:label>
                <form:input type="email" class="form-control" path="email" required="true" />
                <span id="emailValidation" class="validation-message"></span>
            </div>
            <div class="form-group">
                <form:label path="fullName">Full Name:</form:label>
                <form:input type="text" class="form-control" path="fullName" required="true" />
                <span id="fullNameValidation" class="validation-message"></span>
            </div>
            <div class="button-container">
            	<button type="submit" class="btn btn-primary" id="registerButton">Register</button>
            	<a href="login" class="btn btn-default">Login</a>
            </div>
        </form:form>
    </div>

<jsp:include page="../footer.jsp" />
</body>
</html>