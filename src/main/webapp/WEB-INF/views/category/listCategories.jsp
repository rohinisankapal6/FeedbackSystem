<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Categories</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>List Categories</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Category Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>${category.categoryName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="<c:url value="/category/create" />" class="btn btn-primary">Create New Category</a>
    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>