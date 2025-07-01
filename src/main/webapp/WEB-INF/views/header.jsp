<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<style>
    .navbar {
        background-color: #0f1056;
        border: none;
        border-radius: 0;
        margin-bottom: 0;
        position: fixed; /* Make the navbar fixed */
        top: 0;           /* Stick it to the top */
        width: 100%;      /* Ensure it spans the entire width */
        z-index: 1000;    /* Ensure it's above other elements */
    }

    body {
        padding-top: 70px; /* Add padding to the body to prevent content from being hidden behind the navbar. Adjust this value as needed based on the navbar's height. */
    }

    .navbar-brand {
        color: white !important;
        font-weight: bold;
        font-size: 1.5em;
    }

    .navbar-brand:hover {
        color: #f8f9fa;
    }

    .navbar-nav > li > a {
        color: white ;
    }

    .navbar-nav > li > a:hover,
    .navbar-nav > li > a:focus {
        color: white !important;
        background-color: transparent !important;
    }

    .navbar-nav > .active > a,
    .navbar-nav > .active > a:hover,
    .navbar-nav > .active > a:focus {
        color: white !important;
        background-color: #007bff ;
    }

    /* Smaller screens */
    @media (max-width: 768px) {
        .navbar-brand {
            font-size: 1.2em;
        }

        .navbar-nav > li > a {
            padding-top: 10px !important;
            padding-bottom: 10px !important;
            color:white;
        }
    }

    /* Right-aligned navigation */
    .navbar-right {
        float: right !important;
    }

    .navbar-nav  li  a {
        color: white ;
    }
</style>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/home" />">FeedBackManagement</a>
        </div>

        <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${sessionScope.user != null && requestScope['javax.servlet.forward.request_uri'].contains('/userDashboard')}">
                    <li><a href="<c:url value='/home' />"style="color: white;">Home</a></li>
                    <li><a href="<c:url value='/userDashboard' />"style="color: white;">Dashboard</a></li>
                    <li><a href="<c:url value='/userProfile' />"style="color: white;">Profile</a></li>
                    <li><a href="<c:url value='/feedback/submit' />"style="color: white;">Submit Feedback</a></li>
                </c:when>
                <c:when test="${sessionScope.admin != null}">
                    <li><a href="<c:url value='/home' />"style="color: white;">Home</a></li>
                           <li><a href="<c:url value='/login' />"style="color: white;">Login</a></li>
                           <li><a href="<c:url value='/register' />"style="color: white;">Register</a></li>
                           <li><a href="<c:url value='/adminLogin' />"style="color: white;">Admin</a></li>
                    </li>

                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value='/home' />"style="color: white;">Home</a></li>
                </c:otherwise>
            </c:choose>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user != null or sessionScope.admin != null}">
                    <li><a href="<c:url value='/logout' />"style="color: white;">Logout</a></li>
                </c:when>
                <c:when test="${loginClicked}">
                    <li><a href="<c:url value='/register' />"style="color: white;">Register</a></li>
                    <li><a href="<c:url value='/adminLogin' />"style="color: white;">Admin</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value='/login' />"style="color: white;">Login</a></li>
                    <li><a href="<c:url value='/register' />"style="color: white;">Register</a></li>
                    <li><a href="<c:url value='/adminLogin' />"style="color: white;">Admin</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>