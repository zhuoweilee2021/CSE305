<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width:device-width, initial-scale=1">
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
    <title>Users Details</title>
</head>
<body>

<br/>
<h2 style="text-align: center">Users' Details</h2>
<div class="container">
    <c:if test="${empty customers}">
    <h3> Users' details not found! <h3/>
        </c:if>
        <c:if test="${not empty customers}">
        <table class="table table-dark">
            <thead>
            <tr>
                <th>User ID</th>
                <th>SSN</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Zip Code</th>
                <th>Telephone</th>
                <th>Email</th>
                <th>Account Number</th>
                <th>Account Creation Date</th>
                <th>Credit Card Number</th>
                <th>Profile Placement Policy</th>
                <th>Rating</th>
                <th>Date/Time Last Active</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customers}" var="cd">
                <tr>
                    <td>${cd.userID}</td>
                    <td>${cd.userSSN}</td>
                    <td>${cd.firstName}</td>
                    <td>${cd.lastName}</td>
                    <td>${cd.address}</td>
                    <td>${cd.city}</td>
                    <td>${cd.state}</td>
                    <td>${cd.zipCode}</td>
                    <td>${cd.telephone}</td>
                    <td>${cd.email}</td>
                    <td>${cd.accNum}</td>
                    <td>${cd.accCreateDate}</td>
                    <td>${cd.creditCard}</td>
                    <td>${cd.ppp}</td>
                    <td>${cd.rating}</td>
                    <td>${cd.dateLastActive}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
</div>

<div class="container pt-1">
    <form action="home.jsp">
        <input type="submit" value="Home" class="btn btn-success" style="display: table; margin: auto; width: 20%"/>
    </form>
</div>
<br/>
<br/>

<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
