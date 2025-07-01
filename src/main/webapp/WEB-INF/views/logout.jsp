<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logout</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Light gray background */
        }

        .container {
            margin-top: 50px;
            background-color: #ffffff; /* White container background */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            text-align: center; /* Center-align content within the container */
        }

        h1 {
            color: #343a40; /* Dark gray header */
            text-align: center;
        }

        .logout-text {
            font-size: 18px; /* Slightly larger font */
            line-height: 1.6;
            color: #495057; /* Slightly darker text */
            margin-bottom: 20px;
        }

        .return-link {
            margin-top: 15px; /* Add space above the return link */
        }

        a {
            color: #007bff; /* Bootstrap primary color */
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #0056b3; /* Darker blue on hover */
            text-decoration: underline;
        }

        /* Header Styling */
        header {
            background-color: #343a40; /* Dark gray header */
            color: white;
            padding: 15px 0; /* Add padding for better visual appeal */
            text-align: center;
            margin-bottom: 20px;
        }
       .logout-text {
            color: red; /* Customize your logout text color here */
        }

    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1>Logout Successful</h1>
        <p class="logout-text">You have been successfully logged out.</p>
        <p class="return-link"><a href="/logout">Return to home page</a></p>
    </div>

    <jsp:include page="./footer.jsp" />
</body>
</html>