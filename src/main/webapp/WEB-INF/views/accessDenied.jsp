<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Access Denied</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1>Access Denied</h1>
        <p>You do not have permission to access this page.</p>
        <p><a href="home.jsp">Go back to home</a></p>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>