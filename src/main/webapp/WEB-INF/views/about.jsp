<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>About Us</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            color: #495057;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 960px;
            margin: 30px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
            text-align: left;
        }

        h1 {
            color: #007bff;
            margin-bottom: 25px;
            text-align: center;
            font-weight: 600;
            font-size: 2.5em;
        }

        p {
            font-size: 1.1em;
            line-height: 1.7;
            margin-bottom: 15px;
        }


        .benefits {
            margin-top: 20px;
        }

        .benefits p {
            padding-left: 20px;
            position: relative;
        }

        .benefits p:before {
            content: "\f00c";
            font-family: FontAwesome;
            position: absolute;
            left: 0;
            top: 0;
            color: #28a745;
        }


        .header {
            background-color: #007bff;
            color: #fff;
            padding: 20px 0;
            text-align: center;
            margin-bottom: 20px;
        }

        .header h1 {
             color: #fff;
             font-size: 2em;
        }



    </style>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> <!-- Add font awesome link -->
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1>About Us</h1>
        <p>We are a dedicated team passionate about providing a platform for collecting and managing feedback effectively.</p>
        <p>A feedback management system is a tool that helps businesses collect, organize, analyze, and act on customer feedback. It provides a structured approach to gathering insights from various channels, such as surveys, emails, social media, and customer support interactions. </p>

        <div class="benefits">
            <h2>Benefits:</h2>
            <p>Improved Customer Satisfaction: By actively collecting and acting on feedback, businesses can address customer pain points and enhance their overall experience.</p>
            <p>Enhanced Product Development: Feedback provides valuable insights into customer needs and preferences, enabling businesses to develop products that better meet their expectations.</p>
            <p>Increased Customer Loyalty: When customers feel heard and their feedback is valued, they are more likely to become loyal advocates for the business.</p>
        </div>
    </div>

<jsp:include page="./footer.jsp" />
</body>
</html>