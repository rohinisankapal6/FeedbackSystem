<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>

.navbar {
    background-color: 	#0f1056;
    border: none;
    border-radius: 0;
    margin-bottom: 0;
}

.navbar-brand {
    color: white !important;
    font-weight: bold;
    font-size: 1.5em;
    display: inline-block;
}

.navbar-brand:hover {
    color: #f8f9fa !important;
}

.navbar-nav > li > a {
    color: rgba(255, 255, 255, 0.7) !important;
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
        padding-top: 10px ;
        padding-bottom: 10px ;
    }
}

/* Right-aligned navigation */
.navbar-right {
    float: right ;
}



.home-button {
    display: inline-block;
    padding: 15px;
}

.home-button a {
    color: white !important;
    text-decoration: none ;
}

.home-button a:hover {
    color: #f8f9fa ;
}


</style>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
           <a class="navbar-brand" href="<c:url value="/adminDashboard" />">FeedBackManagement</a>
            <div class="home-button">
                <a href="<c:url value="/home" />">Home</a>
            </div>
        </div>
            <ul class="nav navbar-nav navbar-right">

            <li><a href="<c:url value="/logout" />">Logout</a></li>
        </ul>
    </div>
</nav>