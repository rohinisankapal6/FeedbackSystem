<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            /*  Background Image  */
            background-image: url('${pageContext.request.contextPath}/static/Images/fms.png'); /* Assuming it's PNG */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            flex: 1;
        }

        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.1em;
            line-height: 1.6;
            color: #555;
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

        footer {
            background-color: #343a40;
            color: white;
            padding: 20px 0;
            text-align: center;
            margin-top: auto;
        }

        footer p {
            color: rgba(255, 255, 255, 0.7);
            margin: 0;
        }

        header {
            background-color: #007bff;
            color: white;
            padding: 10px 0;
        }

        .navbar-right {
            float: right;
        }
    </style>
</head>
<body>
    <jsp:include page="WEB-INF/views/header.jsp" />

    <div class="container">
        <h1>Welcome to the Feedback Management System</h1>
        <p>Voice of the Customer Platform.</p>
        <p><a href="home">Go to home page</a></p>
    </div>

    <jsp:include page="WEB-INF/views/footer.jsp" />
</body>
</html>