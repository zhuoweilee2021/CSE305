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
	<title>Sales Report</title>
</head>
<body>

<br/>
	<h2 style="text-align: center">Max Revenue by Customer</h2>
	<div class="container" style="width: 35%;margin-top: 6%;">
		<div class="row" style="text-align: center;border: 2px solid;padding: 5%;display: block;">
	<h4>Max Revenue: <c:out value = "${revenue}"/></h4>
			<br/>
	 <c:if test="${empty customers}">
		<h3> Customers not found! <h3/>
	</c:if>
	<c:if test="${not empty customers}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Customer</th>
		    </tr>
		  </thead>
		  <tbody>
		     <c:forEach items="${customers}" var="cd">
		       <tr>
		         <td>${cd}</td>
		         <td></td>
		       </tr>
		     </c:forEach>
		  </tbody>
		</table>
	</c:if>
	</div>


<br/>
	<div class="container pt-1">
		<form action="home.jsp">
			<input type="submit" value="Home" class="btn btn-success" style="display: table; margin: auto !important; width: 50% !important;"/>
		</form>
	</div>

	</div>

	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>
