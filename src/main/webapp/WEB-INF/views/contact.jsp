<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contact Us</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        p {
            font-size: 16px;
            color: #666;
            margin-bottom: 20px;
            text-align: center;
        }

        ul {
            list-style: none;
            padding: 0;
            text-align: center;
        }

        li {
            margin-bottom: 10px;
            font-size: 18px;
            color: #555;
        }

        /* Style for blue color */
        .contact-info {
            color: #007bff; /* Bootstrap's primary blue color */
        }

        /* Media queries for responsiveness */
        @media (max-width: 768px) {
            .container {
                margin: 10px;
                padding: 10px;
            }

            h1 {
                font-size: 24px;
            }

            p {
                font-size: 14px;
            }

            li {
                font-size: 16px;
            }
        }
    </style>

</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1>Contact Us</h1>
        <p>You can reach us at:</p>
        <ul>
            <li>Email: <span class="contact-info">FeedBox@gmail.com</span></li>
            <li>Phone: <span class="contact-info">123-456-7890</span></li>
        </ul>
    </div>

<jsp:include page="./footer.jsp" />
</body>
</html>