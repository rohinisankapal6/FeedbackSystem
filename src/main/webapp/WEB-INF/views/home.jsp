<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        /* Internal CSS for Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            max-width: 960px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-top:5px;
        }

        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.1em;
            line-height: 1.6;
            color:blue;
        }

        a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            display: inline-block;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }


        .header {
            background-color: #007bff;
            color: #fff;
            padding: 10px 0;
            text-align: center;
        }


    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1>Welcome to the Feedback Management System</h1>
        <p>Voice of the Customer Platform</p>
       <img src="static/Images/fms.jpg" style="height:250px; width:80%" alt="FMS Image"/>

        <div>
           <a href="about" class="btn btn-primary">Learn more about us</a>
           <a href="contact" class="btn btn-primary">Contact us</a>
        </div>

    </div>

    <jsp:include page="./footer.jsp" />


</body>
</html>