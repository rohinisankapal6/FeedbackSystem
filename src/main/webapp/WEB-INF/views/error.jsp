<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1>Error</h1>
        <p>An error occurred while processing your request.</p>
        <p>Please try again later.</p>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>