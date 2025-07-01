<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Category</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>Create Category</h1>
        <form:form action="create" method="post" modelAttribute="categoryCommand">
            <div class="form-group">
                <form:label path="categoryName">Category Name:</form:label>
                <form:input type="text" class="form-control" path="categoryName" required="true" />
            </div>
            <button type="submit" class="btn btn-primary">Create</button>
            <a href="<c:url value="/category/list" />" class="btn btn-default">Cancel</a>
        </form:form>
    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>