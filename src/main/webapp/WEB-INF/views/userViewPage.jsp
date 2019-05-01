<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.neu.findmyroomie.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>
</head>
<body bgcolor="#C0C0C0">
	<h2>Hey! Explore the various categories. Right from the temp accommodation to Permanent accomodation  </h2>
	<c:forEach var="category" items="${sessionScope.catList}">
		<ul>
			<li>[<a
				href="/findmyroomie/user/getPost?selectedCategory=${category.categoryName}">${category.categoryName}</a>]&nbsp;&nbsp;
			</li>
		</ul>
	</c:forEach>

	<br />
	<br />
	<h4>Are you also looking for roomies? </h4>
	<h5>Logout and Signup as Seeker!</h5>
	<form action="/findmyroomie/logout.htm" class="form-horizontal"
		method="post">
		<div class="form-group">
			<input type="submit" value="Logout">
		</div>
	</form>

</body>
</html>